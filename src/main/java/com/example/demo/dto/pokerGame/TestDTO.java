package com.example.demo.dto.pokerGame;

public class TestDTO {
    private Long idUser;
    private String url;

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

    public TestDTO(Long idUser, String url) {
        this.idUser = idUser;
        this.url = url;
    }

    public TestDTO() {
    }
}
