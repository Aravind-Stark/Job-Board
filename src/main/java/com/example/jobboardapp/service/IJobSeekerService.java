package com.example.jobboardapp.service;

import java.util.List;

import javax.validation.Valid;

import com.example.jobboardapp.dto.UserLoginDTO;
import org.springframework.stereotype.Service;

import com.example.jobboardapp.dto.JobSeekerDTO;
import com.example.jobboardapp.entities.JobSeeker;

@Service
public interface IJobSeekerService {

	JobSeekerDTO getJobSeekerById(Long id);

	Long getCurrentId();

	JobSeeker createJobSeeker(JobSeeker jobSeeker) throws Exception;

	JobSeeker updateJobSeeker(@Valid Long id, JobSeeker jobSeeker);

	List<JobSeekerDTO> findAllJobSeeker();

	List<JobSeekerDTO> searchBySkill(String skill);

    String jobSeekerLogin(UserLoginDTO userLoginDTO);
}
