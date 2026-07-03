package com.analyzerservice.feignclients;

import org.springframework.stereotype.Component;

import com.analyzerservice.model.ResumeDTO;

@Component
public class ResumeServiceFallback implements ResumeServiceClient {
	@Override
	public ResumeDTO getResume(Long id) {
		return new ResumeDTO(id, "Fallback Candidate", "Fallback resume content");
	}
}
