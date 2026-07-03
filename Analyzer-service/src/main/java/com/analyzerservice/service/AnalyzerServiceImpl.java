package com.analyzerservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.analyzerservice.feignclients.JobServiceClient;
import com.analyzerservice.feignclients.ResumeServiceClient;
import com.analyzerservice.model.AnalysisRequestDTO;
import com.analyzerservice.model.AnalysisResponseDTO;
import com.analyzerservice.model.JobDescriptionDTO;
import com.analyzerservice.model.ResumeDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyzerServiceImpl implements AnalyzerService {

	private final ResumeServiceClient resumeClient;
	private final JobServiceClient jobClient;

	@Override
	@Cacheable(value = "analysisCache", key = "#request.resumeId + '-' + #request.jobId")
	public AnalysisResponseDTO analyzeResume(AnalysisRequestDTO request) {
		ResumeDTO resume = resumeClient.getResume(request.getResumeId());
		JobDescriptionDTO job = jobClient.getJobDescription(request.getJobId());
		int score = calculateWeightedScore(resume.getContent(), job.getDescription());
		String feedback = score > 70 ? "Strong match" : "Needs improvement";
		return new AnalysisResponseDTO(request.getResumeId(), request.getJobId(), score, feedback);
	}

	private int calculateWeightedScore(String resumeText, String jobDescription) {
		String resume = normalize(resumeText);
		String job = normalize(jobDescription);

		Set<String> stopWords = Set.of("requirements", "preferred", "experience", "with", "and", "the", "for", "to",
				"a", "an");

		// Split job description into sections
		String[] parts = job.split("preferred");
		String requirementsPart = parts[0]; // everything before "preferred"
		String preferredPart = parts.length > 1 ? parts[1] : "";

		// Extract tokens
		List<String> requirementTokens = Arrays.stream(requirementsPart.split(" ")).filter(t -> t.length() > 2)
				.filter(t -> !stopWords.contains(t)).toList();

		List<String> preferredTokens = Arrays.stream(preferredPart.split(" ")).filter(t -> t.length() > 2)
				.filter(t -> !stopWords.contains(t)).toList();

		// Tokenize resume
		List<String> resumeTokens = Arrays.stream(resume.split(" ")).filter(t -> t.length() > 2)
				.filter(t -> !stopWords.contains(t)).toList();

		// Calculate weighted matches
		int totalWeight = (requirementTokens.size() * 5) + (preferredTokens.size() * 2);
		int matchedWeight = 0;

		for (String token : requirementTokens) {
			if (resumeTokens.contains(token)) {
				matchedWeight += 5; // higher weight for requirements
			}
		}

		for (String token : preferredTokens) {
			if (resumeTokens.contains(token)) {
				matchedWeight += 2; // lower weight for preferred
			}
		}

		if (totalWeight == 0)
			return 0;
		return (int) ((matchedWeight * 100.0) / totalWeight);
	}

	private String normalize(String text) {
		return text.toLowerCase().replaceAll("[^a-z0-9 ]", " ").replaceAll("\\s+", " ").trim();
	}
}
