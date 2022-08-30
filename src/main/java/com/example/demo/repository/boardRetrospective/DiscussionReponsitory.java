package com.example.demo.repository.boardRetrospective;

import com.example.demo.model.User;
import com.example.demo.model.boardRetrospective.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;

@Repository
public interface DiscussionReponsitory extends JpaRepository<Discussion, Long> {

    //    public List<Discussion> findByboardColunmId(long boardColunmId);
    @Query(" FROM Discussion u WHERE u.boardColunm.id=?1 ORDER BY u.createDate desc ")
    public List<Discussion> findDiscussionByBoardColunmIdOrderByCreateDateDESC(Long boardColunmId);

    @Query(" FROM Discussion u WHERE u.boardColunm.id=?1 ORDER BY u.numberVote desc ")
    public List<Discussion> findDiscussionByBoardColunmIdOrderByNumverVoteDESC(Long boardColunmId);

    @Query(" FROM Discussion u WHERE u.boardColunm.id=?1 ORDER BY u.numberUnvote desc ")
    public List<Discussion> findDiscussionByBoardColunmIdOrderByNumberUnVoteDESC(Long boardColunmId);

    @Query("Select u from Discussion u where u.boardColunm.id = :boradColumnId ORDER BY " +
            "(case when :sortBy = 0 then u.numberUnvote when :sortBy = 1 then u.createDate when :sortBy = 2 then u.numberVote else u.id end) desc")
    public List<Discussion> findDiscussionByBoardColunmIdOrderByNumberUnVoteDESCaaa(@Param("boradColumnId") Long boradColumnId, @Param("sortBy") Integer sortBy);






}