package com.example.demo.dto;

public class ResetPassWordReponse {
    String token;
    String password;

    public ResetPassWordReponse() {
    }

    public ResetPassWordReponse(String token, String password) {
        this.token = token;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
