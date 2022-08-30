package com.example.demo.controller.pokerGame;

import com.example.demo.dto.pokerGame.IssesRepository;
import com.example.demo.helper.ResponseHelper;
import com.example.demo.model.pokerGame.Game;
import com.example.demo.model.pokerGame.Issue;
import com.example.demo.repository.pokerGame.GameRepository;
import com.example.demo.repository.pokerGame.IssuesRepository;
import com.example.demo.services.pokerGame.IssuesService;
import com.example.demo.services.pokerGame.VotingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/planning-poker/issues")
public class IssueController {
    @Autowired
    private IssuesRepository issuesRepo;

    @Autowired private IssuesService issuesService;

    @Autowired
    private GameRepository gameRepository;

    @Autowired private VotingHistoryService votingHistoryService;


    // show issue detail
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssue(@PathVariable long id) {
        return ResponseEntity.ok(issuesRepo.findById(id).get());
    }
    @GetMapping("/game/{id}")
    public ResponseEntity<List<Issue>> getIssuesByIdGame(@PathVariable long id) {
        return ResponseEntity.ok(issuesRepo.findByGame_Id(id));
    }
    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody IssesRepository issesRepository) {

        Game getGameId = gameRepository.findByUrl(issesRepository.getUrl());
        Issue issue = new Issue(issesRepository.getKey(),issesRepository.getTitle(),  getGameId );

        issuesRepo.save(issue);
        return ResponseEntity.ok(issue);
    }
    @GetMapping("/showIssue/{url}")
    public ResponseEntity<List<Issue>> showIssue(@PathVariable String url) {
        List<Issue> getIssueByIdGame  = issuesService.issuesList(url);

        return ResponseEntity.ok(getIssueByIdGame);
       // return ResponseHelper.GenerateResponse(true, "","ISSUE",getIssueByIdGame, HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity editIssue(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        try {
            Issue issues = issuesRepo.findById(id).get();

            for (int i = 0; i < fields.size(); i++) {
                fields.forEach((k, v) -> {
                    Field field = ReflectionUtils.findField(Issue.class, k);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, issues, v);
                });
            }
            try {
                issuesRepo.save(issues);
                return ResponseHelper.GenerateResponse(true, "edit issue success", HttpStatus.OK);

            } catch (Exception e) {
                return ResponseHelper.GenerateResponse(false, "erro ", HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, "khong tim thay issue ", HttpStatus.OK);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteIssue(@PathVariable long id) {
        try {
            issuesRepo.deleteById(id);
            return ResponseHelper.GenerateResponse(true, "delete issue ", HttpStatus.OK);

        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, "error ", HttpStatus.OK);
        }
    }

    @DeleteMapping("/games/{url}")
    public ResponseEntity DeleteIssues(@PathVariable String url) {
        try {
            List<Issue> list = issuesRepo.findByGame_Url(url);
            for (Issue i : list) {
                issuesRepo.deleteById(i.getId());
            }

            return ResponseHelper.GenerateResponse(true, "delete issue ", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseHelper.GenerateResponse(false, " ", HttpStatus.OK);
        }
    }

    @PutMapping("/vote-issue")
    public ResponseEntity votingIssue(@RequestBody Issue issue){
        try {
            votingHistoryService.voteIssue(issue.getId());
//            return ResponseHelper.GenerateResponse(true, "vote issue ", HttpStatus.OK);
            return ResponseHelper.GenerateResponse(true, "vote issue ", "vote success",issuesRepo.findByGame_Url(issuesRepo.findById(issue.getId()).get().getGame().getUrl()), HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);

        }
    }


}
