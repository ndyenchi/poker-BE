package com.example.demo.model.pokerGame;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class IssueHistoryPK implements Serializable {
    @Column(name = "id_issue")
    private Long idIssue;
    @Column(name = "id_history")
    private Long idHistory;

    public IssueHistoryPK() {
    }

    public IssueHistoryPK(Long idIssue, Long idHistory) {
        this.idIssue = idIssue;
        this.idHistory = idHistory;
    }
}
