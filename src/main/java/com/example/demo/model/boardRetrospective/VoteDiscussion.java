package com.example.demo.model.boardRetrospective;

import com.example.demo.model.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "voteDiscussion")
public class VoteDiscussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVote;
    @Column(columnDefinition = "boolean default false")
    private boolean vote;
    @Column(columnDefinition = "boolean default false")
    private boolean unvote;

    @ManyToOne()
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    //
    @ManyToOne()
    @JoinColumn(name = "discussionId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Discussion discussion;

    public VoteDiscussion() {
    }


    public long getIdVote() {
        return idVote;
    }

    public void setIdVote(long idVote) {
        this.idVote = idVote;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public boolean isUnvote() {
        return unvote;
    }

    public void setUnvote(boolean unvote) {
        this.unvote = unvote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }
}
