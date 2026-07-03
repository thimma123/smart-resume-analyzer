package com.analyzerservice.service;

import com.analyzerservice.model.AnalysisRequestDTO;
import com.analyzerservice.model.AnalysisResponseDTO;

public interface AnalyzerService {
    AnalysisResponseDTO analyzeResume(AnalysisRequestDTO request);
}