package com.example.demo.controller.RetrosectiveGame;

import com.example.demo.dto.BoardRetrospectiveDTO;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.boardRetrospective.BoardColunm;
import com.example.demo.model.boardRetrospective.BoardRetrospective;
import com.example.demo.model.boardRetrospective.Discussion;
import com.example.demo.repository.boardRetrospective.BoardColunmReponsitory;
import com.example.demo.repository.boardRetrospective.BoardRetroReponsitory;
import com.example.demo.services.RetrospectiveGame.BoardRetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/retrospective/boards")
public class BoardController {
    @Autowired
    private BoardRetroService boardRetroService;
    @Autowired
    private BoardRetroReponsitory boardRetroReponsitory;
    @Autowired
    private BoardColunmReponsitory boardColunmReponsitory;

    @GetMapping("/{url}")
    public ResponseEntity<BoardRetrospective> getBoard(@PathVariable String url) throws Exception {
        return ResponseEntity.ok(boardRetroService.findBoardByUrl(url));
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<List<BoardRetrospective>> getBoards(@PathVariable Long user_id) {
        return ResponseEntity.ok(boardRetroService.findAllBoardByUserId(user_id));
    }

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardRetrospectiveDTO boardRetrospectiveDTO) throws Exception {
        BoardRetrospective boardRetrospective = boardRetroService.createBoard(boardRetrospectiveDTO);
        return ResponseEntity.ok(new MessageResponse(boardRetrospective.getUrl()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBoard(@RequestBody BoardRetrospective boardRetrospective, @PathVariable long id) throws Exception {
        return ResponseEntity.ok(boardRetroService.editBoard(boardRetrospective, id));
    }

    @DeleteMapping("/{url}")
    public ResponseEntity<BoardRetrospective> deleteBoard(@PathVariable String url) throws Exception {
        return ResponseEntity.ok(boardRetroService.deleteBoard(url));
    }
    @GetMapping("/test")
    public List<BoardColunm> test()throws Exception{
        return boardRetroService.findBoardByUrl1("8a9330a1b98b4fdcb8c0538958e4dc5b");
    }
}
