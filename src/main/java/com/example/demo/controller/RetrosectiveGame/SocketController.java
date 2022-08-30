package com.example.demo.controller.RetrosectiveGame;

import com.example.demo.dto.CommentDisDTO;
import com.example.demo.dto.DiscussionDTO;
import com.example.demo.helper.ResponseHelper;
import com.example.demo.repository.boardRetrospective.DiscussionReponsitory;
import com.example.demo.services.RetrospectiveGame.BoardRetroService;
import com.example.demo.services.RetrospectiveGame.CommentService;
import com.example.demo.services.RetrospectiveGame.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SocketController {
    @Autowired private DiscussionService discussionService;
    @Autowired private BoardRetroService boardRetroService;
    @Autowired private CommentService commentService;

    @MessageMapping("/test")
    @SendTo("/topic/greetings")
    public String test(String mess){
        System.out.println(mess);
        return "test";
    }
    @MessageMapping("/discussion/create")
    @SendTo("/topic/greetings")
    private ResponseEntity createDiscussion(@RequestBody DiscussionDTO discussionDTO) throws Exception {

        discussionService.createDiscussion(discussionDTO);
        return ResponseHelper.GenerateResponse(true,"","DISCUSSION-CREATE",boardRetroService.findBoardByUrl1("5a009ec8835f4d48b69103fdbc597c6a"), HttpStatus.OK);
    }
    @MessageMapping("/comment")
    @SendTo("/topic/greetings")
    private ResponseEntity createComment(@RequestBody CommentDisDTO commentDisDTO) throws Exception {
       commentService.createComment(commentDisDTO);
    //    System.out.println(discussionService.findById(commentDisDTO.getDiscussion_id()).getCommentList().get(0).getUser().getDisplayName());
        return ResponseHelper.GenerateResponse(true,"","COMMENT",discussionService.findById(commentDisDTO.getDiscussion_id()), HttpStatus.OK);
    }
}
