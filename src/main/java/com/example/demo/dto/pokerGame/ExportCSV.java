package com.example.demo.dto.pokerGame;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class ExportCSV {
    private String key;
    private String title;
    private Long average;
    private Long playersVoted;
    private Long playersTotal;
    private Date time;

    public Long getAverage() {
        return average;
    }

    public void setAverage(Long average) {
        this.average = average;
    }

    public Long getPlayersVoted() {
        return playersVoted;
    }

    public void setPlayersVoted(Long playersVoted) {
        this.playersVoted = playersVoted;
    }

    public Long getPlayersTotal() {
        return playersTotal;
    }

    public void setPlayersTotal(Long playersTotal) {
        this.playersTotal = playersTotal;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ExportCSV() {
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
//    public ExportCSV(String issue, Long average, Long playersVoted, Long playersTotal) {
//        this.key = issue;
//        this.average = average;
//        this.playersVoted = playersVoted;
//        this.playersTotal = playersTotal;
//    }


    public ExportCSV(String key, String title, Long average, Long playersVoted, Long playersTotal, Date time) {
        this.key = key;
        this.title = title;
        this.average = average;
        this.playersVoted = playersVoted;
        this.playersTotal = playersTotal;
        this.time = time;
    }
}

