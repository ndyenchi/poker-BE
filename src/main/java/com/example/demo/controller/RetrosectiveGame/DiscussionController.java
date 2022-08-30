package com.example.demo.controller.RetrosectiveGame;

import com.example.demo.dto.DiscussionDTO;
import com.example.demo.dto.SortDTO;
import com.example.demo.model.boardRetrospective.Discussion;
import com.example.demo.services.RetrospectiveGame.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/retrospective/discussions")
@CrossOrigin("*")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @GetMapping
    private ResponseEntity<List<Discussion>> getDiscussions() {
        return ResponseEntity.ok(discussionService.getAllDiscussion());
    }

    @PostMapping
    private ResponseEntity<?> createDiscussion(@RequestBody DiscussionDTO discussionDTO) throws Exception {
        return ResponseEntity.ok(discussionService.createDiscussion(discussionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editDiscussion(@RequestBody Discussion discussion, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(discussionService.editDiscussion(discussion, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Discussion> deleteDiscussion(@PathVariable Long id) throws Exception {
        Discussion discussion = discussionService.deleteDiscussion(id);
        return ResponseEntity.ok(discussion);
    }

    @GetMapping("/sortDiscussion")
    public ResponseEntity<List<Discussion>> SortDiscussion(@RequestParam Long direction, @RequestParam Long boardColumnId) throws Exception {
        List<Discussion> discussionList = null;
        if (direction == null) {
            discussionList = discussionService.findListDiscussionByBoardColumIDOrderByNumberVoteDESC(boardColumnId);
        } else {
            //Sort by numbervote: 0
            if (direction == 0) {
                discussionList = discussionService.findListDiscussionByBoardColumIDOrderByNumberVoteDESC(boardColumnId);
            }
            //Sort by number unvote :1
            if (direction == 1) {
                discussionList = discussionService.findListDiscussionByBoardColumIDOrderByNumberUnVoteDESC(boardColumnId);
            }
            //Sort by create date :2
            if (direction == 2) {
                discussionList = discussionService.findListDiscussionByBoardColumIDOrderByCreateDateDESC(boardColumnId);
            }
        }
        return ResponseEntity.ok(discussionList);
    }


}
