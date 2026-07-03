package com.jobservice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.jobservice.entity.JobDescription;
import com.jobservice.exception.JobNotFoundException;
import com.jobservice.model.JobDescriptionRequestDTO;
import com.jobservice.model.JobDescriptionResponseDTO;
import com.jobservice.repository.JobRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

	private final JobRepository jobRepository;
	private final ModelMapper modelMapper;

	@Override
	@CachePut(value = "jobCache", key = "#result.id")
	public JobDescriptionResponseDTO saveJob(JobDescriptionRequestDTO dto) {
		JobDescription job = modelMapper.map(dto, JobDescription.class);
		JobDescription saved = jobRepository.save(job);
		return modelMapper.map(saved, JobDescriptionResponseDTO.class);
	}

	@Override
	@Cacheable(value = "jobCache", key = "#id")
	public JobDescriptionResponseDTO getJob(Long id) {
		JobDescription job = jobRepository.findById(id)
				.orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
		return modelMapper.map(job, JobDescriptionResponseDTO.class);
	}

	@Override
	public List<JobDescriptionResponseDTO> getAllJobs() {
		return jobRepository.findAll().stream().map(job -> modelMapper.map(job, JobDescriptionResponseDTO.class))
				.toList();
	}

	@Override
	@CachePut(value = "jobCache", key = "#id")
	public JobDescriptionResponseDTO updateJob(Long id, JobDescriptionRequestDTO dto) {
		JobDescription existing = jobRepository.findById(id)
				.orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
		existing.setDescription(dto.getDescription());
		JobDescription updated = jobRepository.save(existing);
		return modelMapper.map(updated, JobDescriptionResponseDTO.class);
	}

	@Override
	@CacheEvict(value = "jobCache", key = "#id")
	public void deleteJob(Long id) {
		JobDescription job = jobRepository.findById(id)
				.orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));
		jobRepository.delete(job);
	}
}