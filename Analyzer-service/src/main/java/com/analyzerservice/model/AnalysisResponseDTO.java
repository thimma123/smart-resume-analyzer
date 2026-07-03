package com.analyzerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisResponseDTO {
    private Long resumeId;
    private Long jobId;
    private int score;
    private String feedback;
}