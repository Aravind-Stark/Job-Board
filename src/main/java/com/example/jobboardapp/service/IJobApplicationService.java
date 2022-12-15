package com.example.jobboardapp.service;

import java.util.List;

import com.example.jobboardapp.dto.JobApplicationStatusDTO;
import com.example.jobboardapp.dto.JobSeekerDetailsDTO;
import org.springframework.stereotype.Service;

import com.example.jobboardapp.dto.JobApplicationDTO;
import com.example.jobboardapp.dto.JobApplicationsListDTO;
import com.example.jobboardapp.entities.JobApplication;


@Service
public interface IJobApplicationService {

	JobApplication applyToJob(JobApplicationDTO jobApplicationDto);

	JobApplication updateJobApplication(Long id, JobApplicationStatusDTO jobApplicationStatusDTO);


	List<JobApplicationsListDTO> findJobSeekersApplications( Long jobSeekerID);

	List<JobSeekerDetailsDTO> viewAllApplicationsByRecruiterId(Long recruiterId);
}