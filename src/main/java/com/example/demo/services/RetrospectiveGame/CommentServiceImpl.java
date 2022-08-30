package com.example.demo.services.RetrospectiveGame;

import com.example.demo.dto.CommentDisDTO;
import com.example.demo.model.User;
import com.example.demo.model.boardRetrospective.Comment;
import com.example.demo.model.boardRetrospective.Discussion;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.boardRetrospective.CommentReponsitory;
import com.example.demo.repository.boardRetrospective.DiscussionReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentReponsitory commentReponsitory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiscussionReponsitory discussionReponsitory;
    @Override
    public List<Comment> listComment() {
        return commentReponsitory.findAll();
    }

    @Override
    public List<Comment> listCommentByDiscussionId(Long discussion_id) throws Exception {
        Discussion discussion=discussionReponsitory.findById(discussion_id)
                .orElseThrow(()->new Exception("Not find discussion with id= "+ ""+discussion_id));
        List<Comment> commentDisListByDiscussionId=commentReponsitory.findBydiscussionId(discussion.getId());
        return commentDisListByDiscussionId;
    }

    @Override
    public Comment createComment(CommentDisDTO commentDisDTO) throws Exception {
        Comment commentDis=new Comment();
        //
        commentDis.setContent(commentDisDTO.getContent());
        //
        User user=userRepository.findById(commentDisDTO.getUser_id())
                .orElseThrow(()->new Exception("Not find user with id= "+""+commentDisDTO.getUser_id()));
        commentDis.setUser(user);
        //
        Discussion discussion=discussionReponsitory.findById(commentDisDTO.getDiscussion_id())
                .orElseThrow(()->new Exception("Not find discussion with id= "+""+commentDisDTO.getDiscussion_id()));
        commentDis.setDiscussion(discussion);
        return commentReponsitory.save(commentDis);
    }
}
