package com.example.jobboardapp.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.example.jobboardapp.dao.IJobSeekerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jobboardapp.dto.JobSeekerDTO;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.exceptions.InvalidJobSeekerException;
import com.example.jobboardapp.service.IJobSeekerService;

@Service
@Transactional
public class JobSeekerServiceImpl implements IJobSeekerService {

	@Autowired
	IJobSeekerDao jobSeekerDao;

	@Override
	public JobSeekerDTO getJobSeekerById(Long id) {
		JobSeekerDTO jobSeekerDTO = new JobSeekerDTO();
		JobSeeker jobSeeker = new JobSeeker();
		if (jobSeekerDao.existsById(id)) {
			jobSeeker =  jobSeekerDao.findById(id).get();
		} else {
			throw new InvalidJobSeekerException();
		}
		jobSeekerDTO.setId(jobSeeker.getId());
		jobSeekerDTO.setFirstName(jobSeeker.getFirstName());
		jobSeekerDTO.setLastName(jobSeeker.getLastName());
		jobSeekerDTO.setEmail(jobSeeker.getEmail());

		return jobSeekerDTO;
	}

	@Override
	public Long getCurrentId() {
		return jobSeekerDao.getCurrentSeriesId();
	}

	@Override
	public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
		if (!(jobSeeker.getFirstName() == null || jobSeeker.getLastName() == null
				|| jobSeeker.getPassword() == null))
			return jobSeekerDao.save(jobSeeker);
		else
			throw new InvalidJobSeekerException();
	}

	@Override
	public JobSeeker updateJobSeeker(Long id, JobSeeker jobSeeker) {
		if (jobSeekerDao.existsById(id)) {
			return jobSeekerDao.save(jobSeeker);
		} else {
			throw new InvalidJobSeekerException();
		}

	}


	@Override
	public List<JobSeekerDTO> findAllJobSeeker() {
		return jobSeekerDao.findAllJobSeeker();
	}

	@Override
	public List<JobSeekerDTO> searchBySkill(String skill) {
		List<JobSeeker> jobSeeker = jobSeekerDao.findBySkillContainingIgnoreCase(skill);
		List<JobSeekerDTO> jobSeekerDTOS = new ArrayList<>();

		for(int i=0;i<jobSeeker.size();i++){
			JobSeekerDTO jobSeekerDTO = new JobSeekerDTO();
			jobSeekerDTO.setId(jobSeeker.get(i).getId());
			jobSeekerDTO.setFirstName(jobSeeker.get(i).getFirstName());
			jobSeekerDTO.setLastName(jobSeeker.get(i).getLastName());
			jobSeekerDTO.setEmail(jobSeeker.get(i).getEmail());
			jobSeekerDTOS.add(jobSeekerDTO);
		}

		return jobSeekerDTOS;
	}

}
