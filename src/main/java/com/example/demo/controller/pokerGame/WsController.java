package com.example.demo.controller.pokerGame;

import com.example.demo.dto.pokerGame.GameUserResponse;
import com.example.demo.dto.pokerGame.IssesRepository;
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
import com.example.demo.repository.pokerGame.IssueHistoryRepository;
import com.example.demo.repository.pokerGame.IssuesRepository;
import com.example.demo.services.pokerGame.GameService;
import com.example.demo.services.pokerGame.GameUserService;
import com.example.demo.services.pokerGame.IssuesService;
import com.example.demo.services.pokerGame.VotingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class WsController {
    @Autowired
    private GameService gameService;
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameUserRepository gameUserRepository;

    @Autowired
    private GameUserService gameUserService;

    @Autowired
    private IssuesRepository issuesRepo;

    @Autowired
    private IssuesService issuesService;

    @Autowired
    private VotingHistoryService votingHistoryService;

    @Autowired
    IssueHistoryRepository issueHistoryRepository;

    @MessageMapping("/choose-card")
    @SendTo("/topic/greetings")

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

            return ResponseHelper.GenerateResponse(true, "true", "CHOOSE", gameUserRepository.findByGame_Url(gameUserResponse.getUrl()), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }

    @MessageMapping("/reveal-card")
    @SendTo("/topic/greetings")
    public ResponseEntity revealCard(@RequestBody RevealCardResponse revealCardResponse) {
        try {

            GameUser gameUser = gameUserRepository.findByGameUrlAndUserID(revealCardResponse.getUrl(), revealCardResponse.getIdUser()).get();
            gameUser.setReveal(revealCardResponse.getReveal());
            gameUserRepository.save(gameUser);
            Issue issue = issuesRepo.findByUrlAndStatus(revealCardResponse.getUrl());
            issue.setAverage(gameUserRepository.avg(gameRepository.findByUrl(revealCardResponse.getUrl()).getId()));


            List<Issue> issueList=issuesService.issuesList(revealCardResponse.getUrl());
            for(int i=0;i< issueList.size();i++){
                if(issueList.get(i).getId() > issue.getId()){
                    issueList.get(i).setStatus(true);
                    Issue temp=issuesRepo.findById(issueList.get(i).getId()).get();
                    temp.setStatus(true);
                    issuesRepo.save(temp);
                    break;
                }
            }
            issue.setStatus(false);
            issuesRepo.save(issue);
            return ResponseHelper.GenerateResponse(true, "revealCard", "REVEAL", issuesService.issuesList(revealCardResponse.getUrl()), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }

    @MessageMapping("/reveal-end")
    @SendTo("/topic/greetings")
    public ResponseEntity revealEnd(@RequestBody RevealCardResponse revealCardResponse) {
        try {
            List<GameUser> lisGameUsers = gameUserRepository.findListGameUserByUrl(revealCardResponse.getUrl());
            for (GameUser gu : lisGameUsers) {
                GameUserID gameUserID = new GameUserID(gu.getUser().getId(), gu.getGame().getId());
                GameUser gameUser = new GameUser(gameUserID);
                gameUserRepository.save(gameUser);
            }
            return ResponseHelper.GenerateResponse(true, "revealCard", "REVEALEND", gameUserRepository.findByGame_Url(revealCardResponse.getUrl()), HttpStatus.OK);

        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }


    @MessageMapping("/issues")
    @SendTo("/topic/greetings")
    public ResponseEntity getIssues(@RequestBody IssesRepository issesRepository) {

        try {

            return ResponseHelper.GenerateResponse(true, "", "ISSUE", issuesService.issuesList(issesRepository.getUrl()), HttpStatus.OK);

        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }

    @MessageMapping("/invite")
    @SendTo("/topic/greetings")
    public ResponseEntity invitePlayer(@RequestBody TestDTO testDTO) {
        try {
            if (gameUserRepository.existsByGameUserIDIdUser(testDTO.getIdUser())) {
                GameUserID gameUserID = new GameUserID(testDTO.getIdUser(), gameRepository.findByUrl(testDTO.getUrl()).getId());
                GameUser gameUser = new GameUser();
                gameUser.setGameUserID(gameUserID);
                gameUserRepository.save(gameUser);
            }
            for (GameUser i : gameUserRepository.findByGame_Url(testDTO.getUrl())) {
                System.out.println(i.getUser().getId());
            }
            return ResponseHelper.GenerateResponse(true, "", "INVITE", gameUserRepository.findByGame_Url(testDTO.getUrl()), HttpStatus.OK);

        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }

    @MessageMapping("/issues/vote")
    @SendTo("/topic/greetings")
    public ResponseEntity vote(@RequestBody TestDTO testDTO) {
        try {
            votingHistoryService.voteIssue(testDTO.getIdUser());
            return ResponseHelper.GenerateResponse(true, "success", "VOTE",
                    issuesService.issuesList(testDTO.getUrl()), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }

    @MessageMapping("/out-game")
    @SendTo("/topic/greetings")
    public ResponseEntity outGame(@RequestBody TestDTO testDTO) {
        try {
            gameUserService.outGame(testDTO);
            return ResponseHelper.GenerateResponse(true, "success", "OUT-GAME", gameUserRepository.findByGame_Url(testDTO.getUrl())
                    , HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }
    @MessageMapping("/issues/delete")
    @SendTo("/topic/greetings")
    public ResponseEntity deleteIssue(TestDTO testDTO) {
        try {
            Issue i=issuesRepo.findById(testDTO.getIdUser()).get();
            if(issueHistoryRepository.existsByIssueHistoryPK_IdIssue(i.getId())){
                i.setDelete(true);
                issuesRepo.save(i);
            }else {
                issuesRepo.delete(i);
            }

            return ResponseHelper.GenerateResponse(true, testDTO.getUrl(), "DELETE-ISSUE", issuesService.issuesList(testDTO.getUrl()), HttpStatus.OK);

        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }
    @MessageMapping("/issues/games/delete")
    @SendTo("/topic/greetings")
    public ResponseEntity deleteAllIssue(@RequestBody IssesRepository issesRepository) {
        try {
            List<Issue> list = issuesRepo.findByGame_Url(issesRepository.getUrl());
            for (Issue i : list) {
                if(issueHistoryRepository.existsByIssueHistoryPK_IdIssue(i.getId())){
                    i.setDelete(true);
                    issuesRepo.save(i);
                }else{
                    Issue issue=issuesRepo.findById(i.getId()).get();
                    issuesRepo.delete(issue);
                }
            }
            return ResponseHelper.GenerateResponse(true, "", "DELETE-ALL-ISSUE", issesRepository.getUrl() , HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }
    @MessageMapping("/issues/edit")
    @SendTo("/topic/greetings")
    public ResponseEntity editIssue(Issue issue) {
        try {
            Issue i=issuesRepo.findById(issue.getId()).get();
            i.setTitle(issue.getTitle());
            i.setDescription(issue.getDescription());
            i.setLink(issue.getLink());
            i.setKey(issue.getKey());
            issuesRepo.save(i);

            return ResponseHelper.GenerateResponse(true, "", "EDIT-ISSUE",issuesRepo.findByGame_Url(i.getGame().getUrl()) , HttpStatus.OK);

        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }

    @MessageMapping("/join-game")
    @SendTo("/topic/greetings")
    public ResponseEntity JoinGame(@RequestBody  TestDTO testDTO){
        try {
            if(gameUserRepository.existsByGameUserIDIdUserAndGame_Url(testDTO.getIdUser(), testDTO.getUrl())){
                System.out.println(true);
                return ResponseHelper.GenerateResponse(true," ","OLD_USER",gameUserRepository.findByGame_Url(testDTO.getUrl()) , HttpStatus.OK);
            }
            else{
                Game game=gameRepository.findByUrl(testDTO.getUrl());
                GameUserID gameUserID = new GameUserID(testDTO.getIdUser(), game.getId());
                User getIdUser = userRepository.findById(testDTO.getIdUser()).get();
                Game getIdGame = gameRepository.findById(game.getId()).get();
                GameUser gameUser = new GameUser(gameUserID, false, getIdUser, getIdGame);
                gameUserRepository.save(gameUser);
                return ResponseHelper.GenerateResponse(true,"","OLD_USER",gameUserRepository.findByGame_Url(testDTO.getUrl()), HttpStatus.OK);

            }

        }catch (Exception e){
            return ResponseHelper.GenerateResponse(false,e.getMessage(), HttpStatus.OK);
        }


    }
}
