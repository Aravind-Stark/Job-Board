package com.example.jobboardapp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.jobboardapp.dto.JobSeekerDTO;
import com.example.jobboardapp.entities.JobSeeker;

@Service
public interface IJobSeekerService {

	JobSeekerDTO getJobSeekerById(Long id);

	Long getCurrentId();

	JobSeeker createJobSeeker(JobSeeker jobSeeker);

	JobSeeker updateJobSeeker(@Valid Long id, JobSeeker jobSeeker);

	List<JobSeekerDTO> findAllJobSeeker();

	List<JobSeekerDTO> searchBySkill(String skill);
}
