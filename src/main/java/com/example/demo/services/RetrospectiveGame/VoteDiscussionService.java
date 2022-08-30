package com.example.demo.services.RetrospectiveGame;

import com.example.demo.dto.VoteDiscussionDTO;
import com.example.demo.model.boardRetrospective.VoteDiscussion;
import org.springframework.stereotype.Service;

@Service
public interface VoteDiscussionService {
    public VoteDiscussion saveDownUpVote(VoteDiscussionDTO voteDiscussionDTO) throws Exception;
    public VoteDiscussion saveDownVote(VoteDiscussionDTO voteDiscussionDTO) throws Exception;
    public VoteDiscussion saveUpVote(VoteDiscussionDTO voteDiscussionDTO) throws Exception;
    public Long countVoteByDiscussion(Long discussionId);
    public Long countUnVoteByDiscussion(Long discussionId);
}
