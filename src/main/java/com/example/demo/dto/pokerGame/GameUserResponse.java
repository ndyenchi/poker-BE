package com.example.demo.dto.pokerGame;

public class GameUserResponse {
    private Long idUser;
    private String url;
    private Integer num;
    private Boolean flip;

    private Boolean reveal;

    public Boolean getReveal() {
        return reveal;
    }

    public void setReveal(Boolean reveal) {
        this.reveal = reveal;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Boolean getFlip() {
        return flip;
    }

    public void setFlip(Boolean flip) {
        this.flip = flip;
    }
}
