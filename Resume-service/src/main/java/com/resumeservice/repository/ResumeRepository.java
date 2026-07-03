package com.resumeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.resumeservice.entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
	
}