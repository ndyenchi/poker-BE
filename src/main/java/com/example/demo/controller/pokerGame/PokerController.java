package com.example.demo.controller.pokerGame;

import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.MessageResponseDTO;
import com.example.demo.dto.pokerGame.GameUserResponse;
import com.example.demo.dto.pokerGame.JoinGameResponse;
import com.example.demo.dto.pokerGame.RevealCardResponse;
import com.example.demo.dto.pokerGame.TestDTO;
import com.example.demo.helper.ResponseHelper;
import com.example.demo.model.User;
import com.example.demo.model.pokerGame.Game;
import com.example.demo.model.pokerGame.GameUser;
import com.example.demo.model.pokerGame.GameUserID;
import com.example.demo.model.pokerGame.Issue;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.pokerGame.GameRepository;
import com.example.demo.repository.pokerGame.GameUserRepository;
import com.example.demo.repository.pokerGame.IssuesRepository;
import com.example.demo.services.pokerGame.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/planning-poker/games")
public class PokerController {

    @Autowired
    private GameService gameService;
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameUserRepository gameUserRepository;

    @Autowired private IssuesRepository issuesRepo;


    @PostMapping
    public ResponseEntity<?> createGame(@Validated @RequestBody JoinGameResponse joinGameResponse) {
        //random string + number
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int len = 25;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Game game = new Game(joinGameResponse.getName(), generatedString);

        Date today = Calendar.getInstance().getTime();
        game.setCreateDate(today);
        gameRepository.save(game);

        //join game
        GameUserID gameUserID = new GameUserID(joinGameResponse.getIdUser(), game.getId());
        User getIdUser = userRepository.findById(joinGameResponse.getIdUser()).get();
        Game getIdGame = gameRepository.findById(game.getId()).get();
        GameUser gameUser = new GameUser(gameUserID, false, getIdUser, getIdGame);
        gameUserRepository.save(gameUser);

        return ResponseEntity.ok(new MessageResponseDTO(generatedString, game.getId()));

    }

    @GetMapping("/{url}")
    public ResponseEntity<Game> getGameByUrl(@PathVariable String url) throws Exception {
        Game game = gameService.FindGameByUrl(url);
        return ResponseEntity.ok(game);

    }


    @GetMapping("/users/games/{url}")
    public ResponseEntity<List<User>> getUsersByIdGame(@PathVariable String url) {
        Game game = gameRepository.findByUrl(url);
        List<GameUser> gameUser = gameUserRepository.findByGame_Id(game.getId());
        List<User> userList = new ArrayList<>();
        for (GameUser gu : gameUser) {
            User user = userRepository.findById(gu.getGameUserID().getIdUser()).get();
            userList.add(user);
        }
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/getGameUserByIdGame/{url}/{userId}")
    public ResponseEntity<GameUser> getGameUserByIdGame(@PathVariable String url, @PathVariable Long userId) {
        return ResponseEntity.ok(gameUserRepository.findByGameUrlAndUserID(url, userId).map(gameUser -> gameUser).orElse(null));
    }
    @GetMapping("/getGamesUserByIdGame/{url}")
    public ResponseEntity<List<GameUser>> getListGameUserByIdGame(@PathVariable String url) {
        Game game = gameRepository.findByUrl(url);
        List<GameUser> gameUser = gameUserRepository.findByGame_Id(game.getId());
        return ResponseEntity.ok(gameUser);
    }

    @PutMapping("/chooseCard")
    public ResponseEntity chooseCard(@RequestBody GameUserResponse gameUserResponse) {
        try {
            GameUserID gameUserID = new GameUserID(gameUserResponse.getIdUser(), gameRepository.findByUrl(gameUserResponse.getUrl()).getId());
            User user = userRepository.findById(gameUserResponse.getIdUser()).get();
            if(gameUserResponse.getFlip()){
                GameUser game = new GameUser(gameUserID,
                        gameUserResponse.getFlip(), user, gameRepository.findByUrl(gameUserResponse.getUrl()), gameUserResponse.getNum());
                gameUserRepository.save(game);
            }else{
                GameUser game = new GameUser(gameUserID,
                        gameUserResponse.getFlip(), user, gameRepository.findByUrl(gameUserResponse.getUrl()), null);
                gameUserRepository.save(game);
            }
            return ResponseHelper.GenerateResponse(true, "true", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseHelper.GenerateResponse(false, "", HttpStatus.OK);
        }
    }
    @GetMapping("/checkRevealCard/{url}")
    public ResponseEntity<Boolean> checkRevealCard(@PathVariable String url) {
        List<GameUser> gameUserList = gameUserRepository.findByGame_Url(url);
        for (GameUser gu : gameUserList) {
            if (gu.getFlipCard() == true)
                return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @GetMapping("/showListVoteTrue/{url}")
    public ResponseEntity<List<GameUser>> showListVoteTrue(@PathVariable String url) {
        List<GameUser> gameUserList = gameUserRepository.findGameUserHaveFlipTrue(url);
        return ResponseEntity.ok(gameUserList);
    }

    @GetMapping("/showAverage/{url}")
    public ResponseEntity<Double> showAverage(@PathVariable String url) {
        Integer total = 0;
        Integer count = 0;
        Double average = 0.0;
        List<GameUser> gameUserList = gameUserRepository.findGameUserHaveFlipTrue(url);
        for (GameUser gu : gameUserList) {
            total = total + gu.getPoint();
            count++;
        }
        if (count == 0)
            return ResponseEntity.ok(0.0);
        average = Double.valueOf((total / count));

        return ResponseEntity.ok(average);
    }

    @GetMapping("/getUserRevealByGameId/{url}")
    public ResponseEntity<Boolean> getUserRevealByGameId(@PathVariable String url) {
        List<GameUser> gameUserList = gameUserRepository.findByGame_Url(url);
        for (GameUser gu : gameUserList) {
            if (gu.getReveal() == true)
                return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @PutMapping("/revealCard")
    public ResponseEntity revealCard(@RequestBody RevealCardResponse revealCardResponse) {
        try {

            GameUser gameUser = gameUserRepository.findByGameUrlAndUserID(revealCardResponse.getUrl(),revealCardResponse.getIdUser()).get();
            gameUser.setReveal(revealCardResponse.getReveal());
            gameUserRepository.save(gameUser);
            Issue issue=issuesRepo.findByUrlAndStatus(revealCardResponse.getUrl());
            issue.setAverage(gameUserRepository.avg(gameRepository.findByUrl(revealCardResponse.getUrl()).getId()));
            issue.setStatus(false);
            issuesRepo.save(issue);
            return ResponseHelper.GenerateResponse(true, "true", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseHelper.GenerateResponse(false, "", HttpStatus.OK);
        }
    }

    @PutMapping("/revealCardEnd")
    public ResponseEntity revealCardEnd(@RequestBody RevealCardResponse revealCardResponse) {
        try {
            List<GameUser> lisGameUsers = gameUserRepository.findListGameUserByUrl(revealCardResponse.getUrl());
            for (GameUser gu : lisGameUsers) {
                GameUserID gameUserID = new GameUserID(gu.getUser().getId(), gu.getGame().getId());
                GameUser gameUser = new GameUser(gameUserID);
                gameUserRepository.save(gameUser);
            }
            return ResponseHelper.GenerateResponse(true, "true", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseHelper.GenerateResponse(false, "", HttpStatus.OK);
        }
    }

    @PostMapping("/invite/{url}/{idUser}")
    public ResponseEntity inviteUser(@PathVariable String url, @PathVariable long idUser) {
        try {
            List<GameUser> gameUserList = gameUserRepository.findByGame_Url(url);
            for (GameUser i : gameUserList) {
                if (i.getGameUserID().getIdUser() == idUser) {
                    return ResponseHelper.GenerateResponse(false, "", HttpStatus.OK);
                } else {
                    GameUserID gameUserID = new GameUserID(idUser, gameUserRepository.findByGame_Url(url).get(0).getGame().getId());
                    GameUser gameUser = new GameUser();
                    gameUser.setGameUserID(gameUserID);
                    gameUserRepository.save(gameUser);
                    return ResponseHelper.GenerateResponse(true, "invite", HttpStatus.OK);
                }
            }
            return ResponseHelper.GenerateResponse(false, "", HttpStatus.OK);

        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }
    
    @PutMapping("/old-user")
    public ResponseEntity oldUser(@RequestBody TestDTO testDTO){

        try {
            if(gameUserRepository.existsByGameUserIDIdUserAndGame_Url(testDTO.getIdUser(), testDTO.getUrl())){
                System.out.println(true);
                return ResponseHelper.GenerateResponse(true," ", HttpStatus.OK);

            }
            else{
                   Game game=gameRepository.findByUrl(testDTO.getUrl());
                GameUserID gameUserID = new GameUserID(testDTO.getIdUser(), game.getId());
                User getIdUser = userRepository.findById(testDTO.getIdUser()).get();
                Game getIdGame = gameRepository.findById(game.getId()).get();
                GameUser gameUser = new GameUser(gameUserID, false, getIdUser, getIdGame);
                gameUserRepository.save(gameUser);
                return ResponseHelper.GenerateResponse(true,"", HttpStatus.OK);

            }

        }catch (Exception e){
            return ResponseHelper.GenerateResponse(false,e.getMessage(), HttpStatus.OK);
        }


    }

}
