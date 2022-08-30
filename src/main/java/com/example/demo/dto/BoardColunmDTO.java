package com.example.demo.dto;

import javax.persistence.Column;

public class BoardColunmDTO {
    private Long id;
    private String title;
    private int order_colum;

    public BoardColunmDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder_colum() {
        return order_colum;
    }

    public void setOrder_colum(int order_colum) {
        this.order_colum = order_colum;
    }
}
