package com.example.demo.model.boardRetrospective;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "board_retrospective")

public class BoardRetrospective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long board_id;
    @Column(name="name")
    private String name;
    @Column(name="url")
    private String url;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "board_boardcolunm", joinColumns = @JoinColumn(name = "board_id"), inverseJoinColumns = @JoinColumn(name = "boardColunm_id"))
    List<BoardColunm> board_Colunms = new ArrayList<BoardColunm>();
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    public BoardRetrospective() {
    }
    public BoardRetrospective(Long board_id, String name, String url) {
        this.board_id = board_id;
        this.name = name;
        this.url = url;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public List<BoardColunm> getBoard_Colunms() {
        return board_Colunms;
    }

    public void setBoard_Colunms(List<BoardColunm> board_Colunms) {
        this.board_Colunms = board_Colunms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
