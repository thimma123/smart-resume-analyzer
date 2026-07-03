package com.jobservice.service;

import java.util.List;
import com.jobservice.model.JobDescriptionRequestDTO;
import com.jobservice.model.JobDescriptionResponseDTO;

public interface JobService {
    JobDescriptionResponseDTO saveJob(JobDescriptionRequestDTO dto);
    JobDescriptionResponseDTO getJob(Long id);
    List<JobDescriptionResponseDTO> getAllJobs();
    JobDescriptionResponseDTO updateJob(Long id, JobDescriptionRequestDTO dto);
    void deleteJob(Long id);
}
