package com.resumeservice.service;

import com.resumeservice.model.ResumeRequestDTO;
import com.resumeservice.model.ResumeResponseDTO;

public interface ResumeService {
    ResumeResponseDTO saveResume(ResumeRequestDTO dto);
    ResumeResponseDTO getResume(Long id);
    ResumeResponseDTO updateResume(Long id, ResumeRequestDTO dto);
    void deleteResume(Long id);
}