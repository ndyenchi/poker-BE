package com.example.demo.controller.pokerGame;

import com.example.demo.dto.pokerGame.ExportCSV;
import com.example.demo.helper.ResponseHelper;
import com.example.demo.repository.pokerGame.GameRepository;
import com.example.demo.repository.pokerGame.GameUserRepository;
import com.example.demo.repository.pokerGame.HistoryRepository;
import com.example.demo.services.pokerGame.ExportCsvService;
import com.example.demo.services.pokerGame.VotingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/planning-poker/voting-history")
public class VotingHistoryController {
    @Autowired
    private GameUserRepository gameUserRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    public HistoryRepository historyRepo;
    @Autowired
    public ExportCsvService exportCsvService;
    @Autowired
    private VotingHistoryService votingHistoryService;

    @GetMapping("/{url}")
    public ResponseEntity<List<ExportCSV>> getVotingHistotys(@PathVariable String url){
        return ResponseEntity.ok(exportCsvService.getData(url));
    }
//    @PostMapping("/{url}")
//    public ResponseEntity saveVotingHistoty(@PathVariable String url, @RequestBody Long idIssue){
//        try {
//            Long avg=gameUserRepository.playersVote(gameRepository.findByUrl(url).getId());
//            Long playersTotal=gameUserRepository.playersTotal(gameRepository.findByUrl(url).getId());
//            Long playersVote=gameUserRepository.playersVote(gameRepository.findByUrl(url).getId());
//            historyRepo.saveVoting(gameRepository.findByUrl(url).getId(),avg, playersTotal, playersVote,idIssue);
//            return ResponseHelper.GenerateResponse(true, "save history", HttpStatus.OK);
//        }catch ( Exception e){
//            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
//        }
//    }

    @PutMapping("/{url}")
    public ResponseEntity saveHistory(@PathVariable String url) {
        try {
            votingHistoryService.saveHistory(url);
            return ResponseHelper.GenerateResponse(true, "success", HttpStatus.OK);

        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);

        }
    }
}
