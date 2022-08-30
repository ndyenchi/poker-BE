package com.example.demo.dto.pokerGame;

public class GameUserDTO {
    private long idUser;
    private boolean flipCard;

    public GameUserDTO() {}

    public GameUserDTO(long idUser, boolean flipCard) {
        this.idUser = idUser;
        this.flipCard = flipCard;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public boolean isFlipCard() {
        return flipCard;
    }

    public void setFlipCard(boolean flipCard) {
        this.flipCard = flipCard;
    }
}
