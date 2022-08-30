package com.example.demo.dto;

public class EmailResponse {
    private String emailTo;

    public EmailResponse() {
    }

    public EmailResponse(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
}
