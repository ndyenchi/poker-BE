package com.example.demo.model.pokerGame;

import com.example.demo.model.User;

import javax.persistence.*;

@Entity
@Table
public class GameUser {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GameUserID gameUserID;

    private Boolean flipCard = false;
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "id_game", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Game game;

    private Integer point;

    private Boolean reveal = false;

    public Boolean getReveal() {
        return reveal;
    }

    public void setReveal(Boolean reveal) {
        this.reveal = reveal;
    }

    public GameUser() {
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public GameUser(GameUserID gameUserID) {
        this.gameUserID = gameUserID;
    }

    public GameUser(GameUserID gameUserID, Boolean reveal) {
        this.gameUserID = gameUserID;
        this.reveal = reveal;
    }

    public GameUser(GameUserID gameUserID, Boolean flipCard, User user, Game game) {
        this.gameUserID = gameUserID;
        this.flipCard = flipCard;
        this.user = user;
        this.game = game;
    }

    public GameUser(GameUserID gameUserID, Boolean flipCard, User user, Game game, Integer point) {
        this.gameUserID = gameUserID;
        this.flipCard = flipCard;
        this.user = user;
        this.game = game;
        this.point = point;
    }

    public GameUser(GameUserID gameUserID, Boolean flipCard, User user, Game game, Integer point, Boolean reveal) {
        this.gameUserID = gameUserID;
        this.flipCard = flipCard;
        this.user = user;
        this.game = game;
        this.point = point;
        this.reveal = reveal;
    }

    public GameUserID getGameUserID() {
        return gameUserID;
    }

    public void setGameUserID(GameUserID gameUserID) {
        this.gameUserID = gameUserID;
    }

    public Boolean getFlipCard() {
        return flipCard;
    }

    public void setFlipCard(Boolean flipCard) {
        this.flipCard = flipCard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
