package com.example.demo.model.pokerGame;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable

public class GameUserID implements Serializable {
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "id_game")
    private Long idGame;

    public GameUserID() {
    }

    public GameUserID(Long idUser, Long idGame) {
        this.idUser = idUser;
        this.idGame = idGame;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }
}
