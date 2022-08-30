package com.example.demo.model.pokerGame;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
@Entity
@Table
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;

    @NotEmpty(message = "not empty")
    @Size(max = 50, message = "<50")
    private String title;

    @Size(max = 300, message = "<300")
    private String description;

    @Size(max = 100, message = "<100")
    private String link;

    private Boolean status=false;

    private Long average;

    @ManyToOne
    @JoinColumn(name = "id_game")
    private Game game;

    private Boolean isDelete=false;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "issue")
    private Collection<IssueHistory> issueHistories;




    public Issue() {
    }

    public Issue(String title, Game game) {
        this.title = title;
        this.game = game;
    }

    public Issue(String title) {
        this.title = title;
    }


    public Issue(String key, String title, Game game) {
        this.key = key;
        this.title = title;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public Long getAverage() {
        return average;
    }

    public void setAverage(Long average) {
        this.average = average;
    }

    public Boolean isDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
