package com.example.demo.model.pokerGame;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_game")
    private Game game;


//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "history")
//    private Set<Issue> issues = new HashSet<>();
//
//    public Set<Issue> getIssues() {
//        return issues;
//    }
//
//    public void setIssues(Set<Issue> issues) {
//        this.issues = issues;
//    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }








    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public History() {
    }


}
