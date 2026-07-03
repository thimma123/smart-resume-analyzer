package com.resumeservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.resumeservice.entity.Resume;
import com.resumeservice.exception.ResumeNotFoundException;
import com.resumeservice.model.ResumeRequestDTO;
import com.resumeservice.model.ResumeResponseDTO;
import com.resumeservice.repository.ResumeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

	private final ResumeRepository resumeRepository;
	private final ModelMapper modelMapper;

	@Override
	@CachePut(value = "resumeCache", key = "#result.id")
	public ResumeResponseDTO saveResume(ResumeRequestDTO dto) {
		Resume resume = modelMapper.map(dto, Resume.class);
		Resume saved = resumeRepository.save(resume);
		return modelMapper.map(saved, ResumeResponseDTO.class);
	}

	@Override
	@Cacheable(value = "resumeCache", key = "#id")
	public ResumeResponseDTO getResume(Long id) {
		Resume resume = resumeRepository.findById(id)
				.orElseThrow(() -> new ResumeNotFoundException("Resume not found with id: " + id));
		return modelMapper.map(resume, ResumeResponseDTO.class);
	}

	@Override
	@CachePut(value = "resumeCache", key = "#id")
	public ResumeResponseDTO updateResume(Long id, ResumeRequestDTO dto) {
		Resume existing = resumeRepository.findById(id)
				.orElseThrow(() -> new ResumeNotFoundException("Resume not found with id: " + id));
		existing.setCandidateName(dto.getCandidateName());
		existing.setContent(dto.getContent());
		Resume updated = resumeRepository.save(existing);

		return modelMapper.map(updated, ResumeResponseDTO.class);
	}

	@Override
	@CacheEvict(value = "resumeCache", key = "#id")
	public void deleteResume(Long id) {
		Resume existing = resumeRepository.findById(id)
				.orElseThrow(() -> new ResumeNotFoundException("Resume not found with id: " + id));
		resumeRepository.delete(existing);
	}
}