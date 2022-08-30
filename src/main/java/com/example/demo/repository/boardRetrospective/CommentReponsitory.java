package com.example.demo.repository.boardRetrospective;

import com.example.demo.model.boardRetrospective.Comment;
import com.example.demo.model.boardRetrospective.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentReponsitory extends JpaRepository<Comment,Long> {
    public List<Comment> findBydiscussionId(Long discussionId);
}