package com.example.demo.services.pokerGame;

import com.example.demo.dto.pokerGame.IssesRepository;
import com.example.demo.model.pokerGame.Issue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssuesService {
    public void createIssue(IssesRepository issesRepository);
    public Issue issue();
    public List<Issue> issuesList(String url);
    public void delete();
    public void deleteAll();

}
