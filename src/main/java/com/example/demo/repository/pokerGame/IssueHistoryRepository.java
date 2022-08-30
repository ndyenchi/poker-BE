package com.example.demo.repository.pokerGame;

import com.example.demo.model.pokerGame.Issue;
import com.example.demo.model.pokerGame.IssueHistory;
import com.example.demo.model.pokerGame.IssueHistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueHistoryRepository extends JpaRepository<IssueHistory, IssueHistoryPK> {
    List<IssueHistory> findByIssue_Game_Url(String url);

    public Boolean existsByIssueHistoryPK_IdIssue(Long idIssue);


}