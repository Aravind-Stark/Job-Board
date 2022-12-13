package com.example.jobboardapp.service;

import java.util.List;

import com.example.jobboardapp.dto.JobListDTO;
import org.springframework.stereotype.Service;

import com.example.jobboardapp.dto.JobDTO;
import com.example.jobboardapp.entities.Job;

@Service
public interface IJobService {

	void close(Long id);

	Job findById(Long id);
	
	List<JobListDTO> findJobsBySkill(String name);
	
	List<JobListDTO> findAll();
	
/*
	List<Object> findAllActiveJobs();
*/

	Job postJob(JobDTO jobDto);
	
	void awardJob(Long jobId, Long jobSeekerId);

	Job updateJob(JobDTO jobDto);

	void deleteJob(Long id);

	List<JobListDTO> findByRecruiterId(Long id);

	List<JobListDTO> findJobsByTitle(String title);

	List<JobListDTO> findJobsByLocation(String location);
}
