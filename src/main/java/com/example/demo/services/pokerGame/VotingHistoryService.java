package com.example.demo.services.pokerGame;

import com.example.demo.dto.pokerGame.ExportCSV;
import com.example.demo.model.pokerGame.History;
import com.example.demo.model.pokerGame.Issue;
import com.example.demo.model.pokerGame.IssueHistory;
import com.example.demo.model.pokerGame.IssueHistoryPK;
import com.example.demo.repository.pokerGame.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class VotingHistoryService {
    @Autowired
    private IssuesRepository issuesRepo;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameUserRepository gameUserRepository;
    @Autowired
    private HistoryRepository historyRepo;
    @Autowired
    private IssueHistoryRepository issueHistoryRepository;


    public void voteIssue(long idIssue) {
        Issue issue=issuesRepo.findById(idIssue).get();
        if(issue.getStatus()){
            issue.setStatus(false);
        }else {
            List<Issue> issueList = issuesRepo.findByGame_Id(issue.getGame().getId());
            for (Issue i : issueList) {
                if (i.getStatus() == true) {
                    i.setStatus(false);
                    issuesRepo.save(i);
                }
            }
            issue.setStatus(true);

        }
        issuesRepo.save(issue);

    }

    public void saveHistory(String url ) {

        Long avs = gameRepository.getByUrl(url).getId();
        Long avg = gameUserRepository.avg(avs);
        Long playersTotal = gameUserRepository.playersTotal(gameRepository.getByUrl(url).getId());
        Long playersVote = gameUserRepository.playersVote(gameRepository.getByUrl(url).getId());




        Date today = Calendar.getInstance().getTime();



        History history = new History();
        history.setGame(gameRepository.findByUrl(url));
        historyRepo.save(history);

        Issue issue=issuesRepo.findByUrlAndStatus(url);
        IssueHistoryPK issueHitoryPK = new IssueHistoryPK(issue.getId(), history.getId());
        IssueHistory issueHistory = new IssueHistory(issueHitoryPK, issue, history, avg, playersVote, playersTotal);
        issueHistory.setTime(today);
        issueHistoryRepository.save(issueHistory);

    }

    public List<ExportCSV> showVoting(String url) {
        List<ExportCSV> votingList = new ArrayList<>();
        List<IssueHistory> issueHistoryList=issueHistoryRepository.findByIssue_Game_Url(url);
        for(IssueHistory ih: issueHistoryList){
            ExportCSV exportCSV=new ExportCSV(ih.getIssue().getKey(),ih.getIssue().getTitle(), ih.getAverage(), ih.getPlayersVoted(), ih.getPlayersVoted(), ih.getTime());
            votingList.add(exportCSV);
        }

        return votingList;
    }
}
