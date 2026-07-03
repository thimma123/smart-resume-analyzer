package com.jobservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobservice.model.JobDescriptionRequestDTO;
import com.jobservice.model.JobDescriptionResponseDTO;
import com.jobservice.service.JobService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job")
public class JobController {

	private final JobService jobService;

	@PostMapping
	public ResponseEntity<JobDescriptionResponseDTO> saveJob(@RequestBody JobDescriptionRequestDTO dto) {
		return ResponseEntity.ok(jobService.saveJob(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<JobDescriptionResponseDTO> getJob(@PathVariable Long id) {
		return ResponseEntity.ok(jobService.getJob(id));
	}

	@GetMapping
	public ResponseEntity<List<JobDescriptionResponseDTO>> getAllJobs() {
		return ResponseEntity.ok(jobService.getAllJobs());
	}

	@PutMapping("/{id}")
	public ResponseEntity<JobDescriptionResponseDTO> updateJob(@PathVariable Long id,
			@RequestBody JobDescriptionRequestDTO dto) {
		return ResponseEntity.ok(jobService.updateJob(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id) {
		jobService.deleteJob(id);
		return ResponseEntity.ok("Job deleted successfully with id: " + id);
	}
}
