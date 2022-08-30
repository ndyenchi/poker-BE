package com.example.demo.dto;

public class CommentDisDTO {

    private String content;
    private Long user_id;
    private Long discussion_id;

    public CommentDisDTO() {
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

    public Long getDiscussion_id() {
        return discussion_id;
    }

    public void setDiscussion_id(Long discussion_id) {
        this.discussion_id = discussion_id;
    }
}
