package com.example.demo.services.pokerGame.impl;

import com.example.demo.dto.pokerGame.IssesRepository;
import com.example.demo.model.pokerGame.Game;
import com.example.demo.model.pokerGame.Issue;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.pokerGame.GameRepository;
import com.example.demo.repository.pokerGame.GameUserRepository;
import com.example.demo.repository.pokerGame.IssuesRepository;
import com.example.demo.services.pokerGame.GameService;
import com.example.demo.services.pokerGame.GameUserService;
import com.example.demo.services.pokerGame.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesServiceImpl implements IssuesService {
    @Autowired
    private GameService gameService;
    @Autowired private GameRepository gameRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private GameUserRepository gameUserRepository;

    @Autowired private GameUserService gameUserService;

    @Autowired private IssuesRepository issuesRepo;
    @Override
    public void createIssue(IssesRepository issesRepository) {
        Game getGameId = gameRepository.findByUrl(issesRepository.getUrl());
        Issue issue = new Issue(issesRepository.getKey(), issesRepository.getTitle(), getGameId);
        issuesRepo.save(issue);
    }

    @Override
    public Issue issue() {
        return null;
    }

    @Override
    public List<Issue> issuesList(String url) {
       return issuesRepo.findByIsDeleteAndGame_UrlOrderByIdAsc(false,url);

    }

    @Override
    public void delete() {

    }

    @Override
    public void deleteAll() {

    }
}
