package com.example.demo.services.pokerGame;

import com.example.demo.dto.pokerGame.ExportCSV;
import com.example.demo.model.pokerGame.History;
import com.example.demo.model.pokerGame.IssueHistory;
import com.example.demo.repository.pokerGame.GameRepository;
import com.example.demo.repository.pokerGame.HistoryRepository;
import com.example.demo.repository.pokerGame.IssueHistoryRepository;
import com.example.demo.repository.pokerGame.IssuesRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportCsvService {

    @Autowired
    private GameRepository gamerepo;
    @Autowired
    private HistoryRepository historyRepo;
    @Autowired
    private IssuesRepository issuesRepo;
    @Autowired
    private IssueHistoryRepository issueHistoryRepository;


    private List<History> histories;
    public List<ExportCSV> csv;

    public List<ExportCSV> getData(String url) {
        csv = new ArrayList<>();


        List<IssueHistory> list = issueHistoryRepository.findByIssue_Game_Url(url);
        for (IssueHistory ih : list) {
            ExportCSV exportCSV = new ExportCSV(ih.getIssue().getKey(), ih.getIssue().getTitle(), ih.getAverage(),
                    ih.getPlayersTotal(), ih.getPlayersVoted(), ih.getTime());
            csv.add(exportCSV);
        }
        return csv;
    }

    public void writeToCsv(String url, Writer writer) throws IOException {
        List<String> header = new ArrayList<>();
        header.add("Issue");
        header.add("Average");
        header.add("Players voted");
        header.add("Players total");
        header.add("Time");
        CSVPrinter csvPrinter1 = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header.toArray(new String[0])));
        List<IssueHistory> list = issueHistoryRepository.findByIssue_Game_Url(url);

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (IssueHistory h : list) {
                csvPrinter.printRecord(h.getIssue().getKey()+"-"+h.getIssue().getTitle(), h.getAverage(), h.getPlayersTotal(), h.getPlayersVoted(), h.getTime());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
