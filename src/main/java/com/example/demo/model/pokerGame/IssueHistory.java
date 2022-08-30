package com.example.demo.model.pokerGame;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class IssueHistory {
    @EmbeddedId
    protected IssueHistoryPK issueHistoryPK;

    @JoinColumn(name = "id_issue", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Issue issue;

    @JoinColumn(name = "id_history", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private History history;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date time;

    private Long average;
    private Long playersTotal;
    private Long playersVoted;

    public IssueHistoryPK getIssueHistoryPK() {
        return issueHistoryPK;
    }

    public void setIssueHistoryPK(IssueHistoryPK IssueHistoryPK) {
        this.issueHistoryPK = IssueHistoryPK;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getAverage() {
        return average;
    }

    public void setAverage(Long average) {
        this.average = average;
    }

    public Long getPlayersTotal() {
        return playersTotal;
    }

    public void setPlayersTotal(Long playersTotal) {
        this.playersTotal = playersTotal;
    }

    public Long getPlayersVoted() {
        return playersVoted;
    }

    public void setPlayersVoted(Long playersVoted) {
        this.playersVoted = playersVoted;
    }

    public IssueHistory(IssueHistoryPK issueHistoryPK, Issue issue, History history, Long average, Long playersTotal, Long playersVoted) {
        this.issueHistoryPK = issueHistoryPK;
        this.issue = issue;
        this.history = history;
    //    this.time = time;
        this.average = average;
        this.playersTotal = playersTotal;
        this.playersVoted = playersVoted;
    }

    public IssueHistory() {
    }
}
