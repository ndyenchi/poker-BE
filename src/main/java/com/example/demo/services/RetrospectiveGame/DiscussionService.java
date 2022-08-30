package com.example.demo.services.RetrospectiveGame;

import com.example.demo.dto.DiscussionDTO;
import com.example.demo.model.boardRetrospective.Discussion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiscussionService {
    List<Discussion> getAllDiscussion();
    Discussion createDiscussion(DiscussionDTO discussionDTO) throws Exception;

    Discussion editDiscussion(Discussion discussion,long id) throws Exception;

    Discussion deleteDiscussion(long id) throws Exception;
    public List<Discussion> findListDiscussionByBoardColumIDOrderByCreateDateDESC(long boardColunmId) throws Exception;
    public List<Discussion> findListDiscussionByBoardColumIDOrderByNumberVoteDESC(long boardColunmId) throws Exception;
    public List<Discussion> findListDiscussionByBoardColumIDOrderByNumberUnVoteDESC(long boardColunmId) throws Exception;

    public Discussion findById(Long id);

}
