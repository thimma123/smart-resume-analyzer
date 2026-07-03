package com.analyzerservice.feignclients;

import org.springframework.stereotype.Component;

import com.analyzerservice.model.JobDescriptionDTO;

@Component
public class JobServiceFallback implements JobServiceClient {
    @Override
    public JobDescriptionDTO getJobDescription(Long id) {
        return new JobDescriptionDTO(id, "Fallback job description");
    }
}