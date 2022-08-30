package com.example.demo.dto.pokerGame;

public class JoinGameResponse {

    private Long idUser;

    private String name;

    public JoinGameResponse() {
    }

    public Long getId() {
        return idUser;
    }

    public void setId(Long idUser) {
        this.idUser = idUser;
    }

    public JoinGameResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
