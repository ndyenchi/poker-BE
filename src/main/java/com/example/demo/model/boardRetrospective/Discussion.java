package com.example.demo.model.boardRetrospective;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.demo.model.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name="Discustion")
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="content",length = 500)
    private String content;

    @Column(name="numberVote")
    private Long numberVote;

    @Column(name="numberUnvote")
    private Long numberUnvote;

    private LocalDateTime createDate;

    @ManyToOne()
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="boardColunm_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BoardColunm boardColunm;

    @OneToMany(mappedBy = "discussion", fetch = FetchType.EAGER)
    List<Comment> commentList ;


    @Transient
    private String displayName;

    public Discussion(String content) {
        this.content = content;
    }


    public Discussion(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BoardColunm getBoardColunm() {
        return boardColunm;
    }

    public void setBoardColunm(BoardColunm boardColunm) {
        this.boardColunm = boardColunm;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }


}
