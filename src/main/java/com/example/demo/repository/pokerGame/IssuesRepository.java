package com.example.demo.repository.pokerGame;

import com.example.demo.model.pokerGame.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuesRepository extends JpaRepository<Issue, Long> {
    public List<Issue> findByGame_Id(Long id);

    @Query("select i from Issue i where i.game.url = :url ORDER BY i.id ASC")
    public List<Issue> findByGame_Url(@Param("url") String url);

    @Query(value = "select i from Issue i where i.id =:id")
    public Issue getIssueByID(@Param("id") long id);

    @Query(value = "select i from Issue i where i.game.url =:url and i.status = true")
    public Issue findByUrlAndStatus(@Param("url") String id);


    public List<Issue> findByIsDeleteAndGame_Id(Boolean isDelete, Long id);

    @Query("select i from Issue i where i.game.url = :url and isDelete = :isDelete ORDER BY i.id ASC")
    public List<Issue> findByIsDeleteAndGame_UrlOrderByIdAsc(@Param("isDelete")Boolean isDelete, @Param("url")String url);

}