package com.jobservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jobservice.entity.JobDescription;

@Repository
public interface JobRepository extends JpaRepository<JobDescription, Long> {
	
}