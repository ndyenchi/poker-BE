package com.example.demo.controller.pokerGame;

import com.example.demo.services.pokerGame.ExportCsvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RestController
@RequestMapping("/api/planning-poker/")
public class ExportCsvController {
    private final ExportCsvService csvService;

    public ExportCsvController(ExportCsvService csvService) {
        this.csvService = csvService;
    }
    @GetMapping("/exportCSVs/{id}")
    public ResponseEntity<String> exportCsv(@PathVariable String id, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"export-csv.csv\"");
        csvService.writeToCsv(id,servletResponse.getWriter());
        return ResponseEntity.ok("Export CSV success");
    }
}
