package com.example.demo.model.boardRetrospective;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="board_column")
public class BoardColunm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardColunm_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "order_colum")
    private int order_colum;

    @OneToMany(mappedBy = "boardColunm",fetch = FetchType.LAZY)
    List<Discussion> discussionList ;
    public BoardColunm() {

    }

    public BoardColunm(String title, int order_colum) {
        this.title = title;
        this.order_colum = order_colum;
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

    public List<Discussion> getDiscussionList() {
        return discussionList;
    }

    public void setDiscussionList(List<Discussion> discussionList) {
        this.discussionList = discussionList;
    }
}
