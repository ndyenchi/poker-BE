package com.example.demo.dto;

import java.time.LocalDateTime;

public class DiscussionDTO {
    private String content;
    private Long user_id;
    private Long boardColunm_id;
    private Long numberVote;
    private Long numberUnvote;

    public DiscussionDTO() {
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getBoardColunm_id() {
        return boardColunm_id;
    }

    public void setBoardColunm_id(Long boardColunm_id) {
        this.boardColunm_id = boardColunm_id;
    }

    public Long getNumberVote() {
        return numberVote;
    }

    public void setNumberVote(Long numberVote) {
        this.numberVote = numberVote;
    }

    public Long getNumberUnvote() {
        return numberUnvote;
    }

    public void setNumberUnvote(Long numberUnvote) {
        this.numberUnvote = numberUnvote;
    }

}

