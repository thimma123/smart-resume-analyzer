package com.analyzerservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.analyzerservice.model.AnalysisRequestDTO;
import com.analyzerservice.model.AnalysisResponseDTO;
import com.analyzerservice.service.AnalyzerService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/analyzer")
public class AnalyzerController {

    private final AnalyzerService analyzerService;

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResponseDTO> analyze(@RequestBody AnalysisRequestDTO request) {
        return ResponseEntity.ok(analyzerService.analyzeResume(request));
    }
}
