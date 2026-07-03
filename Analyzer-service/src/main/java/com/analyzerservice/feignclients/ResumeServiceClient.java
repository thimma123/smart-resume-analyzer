package com.analyzerservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.analyzerservice.model.ResumeDTO;

@FeignClient(name="resume-service", fallback=ResumeServiceFallback.class)
public interface ResumeServiceClient {
    @GetMapping("/api/resume/{id}")
    ResumeDTO getResume(@PathVariable("id") Long id);
}
