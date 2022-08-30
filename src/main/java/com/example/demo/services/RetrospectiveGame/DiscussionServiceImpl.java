package com.example.demo.services.RetrospectiveGame;

import com.example.demo.dto.DiscussionDTO;
import com.example.demo.dto.retro.CommentDTO1;
import com.example.demo.dto.retro.DiscussionDTO1;
import com.example.demo.model.User;
import com.example.demo.model.boardRetrospective.BoardColunm;
import com.example.demo.model.boardRetrospective.Discussion;
import com.example.demo.repository.boardRetrospective.BoardColunmReponsitory;
import com.example.demo.repository.boardRetrospective.DiscussionReponsitory;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired
    private DiscussionReponsitory discussionReponsitory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardColunmReponsitory boardColunmReponsitory;
    @Override
    public List<Discussion> getAllDiscussion() {
        return discussionReponsitory.findAll();
    }
    @Override
    public Discussion createDiscussion(DiscussionDTO discusssionDTO) throws Exception {
        Discussion discussion = new Discussion();
        discussion.setContent(discusssionDTO.getContent());
        discussion.setCreateDate(LocalDateTime.now());

        BoardColunm boardColunm = boardColunmReponsitory.findById(discusssionDTO.getBoardColunm_id()).
                orElseThrow(()->new Exception("Cant not found boardcolumn_id"+discusssionDTO.getBoardColunm_id()));

        User user = userRepository.findById(discusssionDTO.getUser_id()).
                orElseThrow(()->new Exception("Cant not found user_id"+discusssionDTO.getUser_id()));

        discussion.setUser(user);

        discussion.setBoardColunm(boardColunm);
        discussion.setNumberUnvote(discusssionDTO.getNumberUnvote() != null ? discusssionDTO.getNumberUnvote() : 0);
        discussion.setNumberVote(discusssionDTO.getNumberVote() != null ? discusssionDTO.getNumberVote() : 0);
        return discussionReponsitory.save(discussion);
    }

    @Override
    public Discussion editDiscussion(Discussion discussion, long id) throws Exception {
        Discussion discussion1 = discussionReponsitory.findById(id)
                .orElseThrow(() -> new Exception("Cant not find id "+ id));
        discussion1.setContent(discussion.getContent());
        discussionReponsitory.save(discussion1);
        return discussion1;
    }
    @Override
    public Discussion deleteDiscussion(long id) throws Exception {
        Discussion discussion = discussionReponsitory.findById(id)
                .orElseThrow(() -> new Exception("Cant not find id "+ id));
        discussionReponsitory.delete(discussion);
        return discussion;
    }
    @Override
    public List<Discussion> findListDiscussionByBoardColumIDOrderByCreateDateDESC(long boardColunmId) throws Exception {
        BoardColunm boardColunm = boardColunmReponsitory.findById(boardColunmId)
                .orElseThrow(() -> new Exception("Not find board colunm with id= " + boardColunmId));
        List<Discussion> discussionListbyBoardColunmId =
                discussionReponsitory.findDiscussionByBoardColunmIdOrderByCreateDateDESC(boardColunm.getId());
        return discussionListbyBoardColunmId;
    }
    @Override
    public List<Discussion> findListDiscussionByBoardColumIDOrderByNumberVoteDESC(long boardColunmId) throws Exception {
        BoardColunm boardColunm=boardColunmReponsitory.findById(boardColunmId)
                .orElseThrow(()->new Exception("Not find board colunm with id= "+boardColunmId));
        List<Discussion>discussionListbyBoardColunmId=
                discussionReponsitory.findDiscussionByBoardColunmIdOrderByNumverVoteDESC(boardColunm.getId());
        return discussionListbyBoardColunmId;
    }
    @Override
    public List<Discussion> findListDiscussionByBoardColumIDOrderByNumberUnVoteDESC(long boardColunmId) throws Exception {
        BoardColunm boardColunm=boardColunmReponsitory.findById(boardColunmId)
                .orElseThrow(()->new Exception("Not find board colunm with id= "+boardColunmId));
        List<Discussion>discussionListbyBoardColunmId=
                discussionReponsitory.findDiscussionByBoardColunmIdOrderByNumberUnVoteDESC(boardColunm.getId());
        return discussionListbyBoardColunmId;
    }
    @Override
    public Discussion findById(Long id) {
        Discussion discussion = discussionReponsitory.findById(id).get();
        if (!CollectionUtils.isEmpty(discussion.getCommentList())) {
            discussion.getCommentList().forEach(comment -> {
                Optional<User> userOptional = userRepository.findById(comment.getUser().getId());

                comment.setDisplayName(userOptional.isPresent() ? userOptional.get().getDisplayName() : "");
            });
        }
        return discussion;
    }

}
