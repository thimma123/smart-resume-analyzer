package com.resumeservice.controller;

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
import com.resumeservice.model.ResumeRequestDTO;
import com.resumeservice.model.ResumeResponseDTO;
import com.resumeservice.service.ResumeService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeController {

	private final ResumeService resumeService;

	@PostMapping
	public ResponseEntity<ResumeResponseDTO> saveResume(@RequestBody ResumeRequestDTO dto) {
		return ResponseEntity.ok(resumeService.saveResume(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResumeResponseDTO> getResume(@PathVariable Long id) {
		return ResponseEntity.ok(resumeService.getResume(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResumeResponseDTO> update(@PathVariable Long id, @RequestBody ResumeRequestDTO dto) {
		return ResponseEntity.ok(resumeService.updateResume(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		resumeService.deleteResume(id);
		return ResponseEntity.ok("Resume deleted successfully with id: " + id);
	}
}
