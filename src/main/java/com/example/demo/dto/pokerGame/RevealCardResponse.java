package com.example.demo.dto.pokerGame;

public class RevealCardResponse {

    private Long idUser;
    private String url;
    private Boolean reveal;

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

    public Boolean getReveal() {
        return reveal;
    }

    public void setReveal(Boolean reveal) {
        this.reveal = reveal;
    }
}
