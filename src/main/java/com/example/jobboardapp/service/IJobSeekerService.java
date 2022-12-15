package com.example.jobboardapp.service;

import java.util.List;

import javax.validation.Valid;

import com.example.jobboardapp.dto.JobSeekerRegisterDTO;
import com.example.jobboardapp.dto.UserLoginDTO;
import org.springframework.stereotype.Service;

import com.example.jobboardapp.dto.JobSeekerDTO;
import com.example.jobboardapp.entities.JobSeeker;

@Service
public interface IJobSeekerService {

	JobSeekerDTO getJobSeekerById(Long id);

	Long getCurrentId();

	JobSeeker createJobSeeker(JobSeekerRegisterDTO jobSeekerRegisterDTO) throws Exception;

	JobSeeker updateJobSeeker( Long id, JobSeekerRegisterDTO jobSeekerRegisterDTO);

	List<JobSeekerDTO> findAllJobSeeker();

	List<JobSeekerDTO> searchBySkill(String skill);

    String jobSeekerLogin(UserLoginDTO userLoginDTO);
}
