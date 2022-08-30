package com.example.demo.dto;

public class MessageResponseDTO {
    private String message;
    private Long id;



    public MessageResponseDTO(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
