package com.example.demo.dto;

public class SortDTO {
    Long boardColumnId;
    String sortBy; // ASC, DESC
    Long direction;  //createDate 0, vote 1, unvote 2

    public Long getBoardColumnId() {
        return boardColumnId;
    }

    public void setBoardColumnId(Long boardColumnId) {
        this.boardColumnId = boardColumnId;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Long getDirection() {
        return direction;
    }

    public void setDirection(Long direction) {
        this.direction = direction;
    }
}
