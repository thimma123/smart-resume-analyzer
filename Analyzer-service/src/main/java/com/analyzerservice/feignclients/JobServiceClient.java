package com.analyzerservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.analyzerservice.model.JobDescriptionDTO;

@FeignClient(name="job-service", fallback=JobServiceFallback.class)
public interface JobServiceClient {
    @GetMapping("/api/job/{id}")
    JobDescriptionDTO getJobDescription(@PathVariable("id") Long id);
}
