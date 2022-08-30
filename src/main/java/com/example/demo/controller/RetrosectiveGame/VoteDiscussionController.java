package com.example.demo.controller.RetrosectiveGame;

import com.example.demo.dto.VoteDiscussionDTO;
import com.example.demo.model.boardRetrospective.VoteDiscussion;
import com.example.demo.services.RetrospectiveGame.VoteDiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/retrospective/discussion")
@CrossOrigin("*")
public class VoteDiscussionController {
    @Autowired
    private VoteDiscussionService voteDiscussionService;

    @PostMapping("/vote")
    public ResponseEntity<VoteDiscussion> saveVote(@RequestBody VoteDiscussionDTO voteDiscussionDTO) throws Exception {
        VoteDiscussion voteDiscussion = voteDiscussionService.saveUpVote(voteDiscussionDTO);
        return ResponseEntity.ok(voteDiscussion);
    }

    @PostMapping("/unvote")
    public ResponseEntity<VoteDiscussion> saveUnvote(@RequestBody VoteDiscussionDTO voteDiscussionDTO) throws Exception {
        VoteDiscussion voteDiscussion = voteDiscussionService.saveDownVote(voteDiscussionDTO);
        return ResponseEntity.ok(voteDiscussion);
    }

    @GetMapping("/count/vote")
    public ResponseEntity<Long> countVote(@RequestParam Long discussionId) {
        Long count = voteDiscussionService.countVoteByDiscussion(discussionId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/unvote")
    public ResponseEntity<Long> countUnvote(@RequestParam Long discussionId) {
        Long count = voteDiscussionService.countUnVoteByDiscussion(discussionId);
        return ResponseEntity.ok(count);
    }
}
