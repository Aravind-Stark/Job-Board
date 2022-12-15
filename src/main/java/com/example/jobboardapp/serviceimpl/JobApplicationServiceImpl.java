package com.example.jobboardapp.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.example.jobboardapp.dao.IJobSeekerDao;
import com.example.jobboardapp.dao.IRecruiterDao;
import com.example.jobboardapp.dto.JobApplicationStatusDTO;
import com.example.jobboardapp.dto.JobSeekerDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jobboardapp.dao.IJobApplicationDao;
import com.example.jobboardapp.dao.IJobDao;
import com.example.jobboardapp.dto.JobApplicationDTO;
import com.example.jobboardapp.dto.JobApplicationsListDTO;
import com.example.jobboardapp.entities.JobApplication;
import com.example.jobboardapp.exceptions.InvalidJobApplicationException;
import com.example.jobboardapp.service.IJobApplicationService;


@Service
@Transactional
public class JobApplicationServiceImpl implements IJobApplicationService {

	@Autowired
	IJobApplicationDao jobApplicationDao;

	@Autowired
	IJobDao jobDao;

	@Autowired
	IRecruiterDao recruiterDao;

	@Autowired
	IJobSeekerDao jobSeekerDao;

	@Override
	public JobApplication applyToJob(JobApplicationDTO jobApplicationDto) {
		JobApplication jobApplication = new JobApplication();
        Long jsId =  jobApplicationDao.findByJobSeekerIdAndJobId(jobApplicationDto.getJobseekerId(),jobApplicationDto.getJobId());
		if (!(jsId== jobApplicationDto.getJobseekerId()) && (jobDao.existsById(jobApplicationDto.getJobId()) && jobApplicationDto.getJobseekerId() != null)
				&& jobApplicationDto.getJobId() != null ) {
			jobApplication.setJobSeeker(jobSeekerDao.findById(jobApplicationDto.getJobseekerId()).get());
			jobApplication.setJob(jobDao.findById(jobApplicationDto.getJobId()).get());
			jobApplication.setRecruiter(recruiterDao.findById(jobApplicationDto.getRecruiterId()).get());
			jobApplication.setJob(jobDao.findById(jobApplicationDto.getJobId()).get());
			jobApplication.setStatus("In Progress");

			return jobApplicationDao.save(jobApplication);
			
		} else {
			throw new InvalidJobApplicationException();
		}
	}


	@Override
	public JobApplication updateJobApplication(Long id, JobApplicationStatusDTO jobApplicationStatusDTO) {
		if (jobApplicationDao.existsById(id)) {
			JobApplication jobApplication = jobApplicationDao.findById(id).get();
			jobApplication.setStatus(jobApplicationStatusDTO.getStatus());
			jobApplicationDao.save(jobApplication);

			return jobApplication;
		} else {
			throw new InvalidJobApplicationException();
		}

	}

	@Override
	public List<JobApplicationsListDTO> findJobSeekersApplications( Long jobSeekerID) {
		/*List<JobApplication> jobApplication = jobApplicationDao.findByJobSeekerId(jobSeekerID);
		System.out.println(jobApplication.get(0));
		List<JobApplicationsListDTO> jobApplicationsListDTOS = new ArrayList<>();
		for(int i=0;i<jobApplication.size();i++){
			JobApplicationsListDTO jobApplicationsListDTO = new JobApplicationsListDTO();
			jobApplicationsListDTO.setId(jobApplication.get(i).getId());
			jobApplicationsListDTO.setJobId(jobApplication.get(i).getJob().getId());
			jobApplicationsListDTO.setJobTitle(jobApplication.get(i).getJob().getJobTitle());
			jobApplicationsListDTO.setJobSeekerId(jobApplication.get(i).getJobSeeker().getId());
			jobApplicationsListDTO.setStatus(jobApplication.get(i).getStatus());
			jobApplicationsListDTOS.add(jobApplicationsListDTO);
		}*/
		return jobApplicationDao.findJobSeekersApplications(jobSeekerID);
		//return null;
	}

	@Override
	public List<JobSeekerDetailsDTO> viewAllApplicationsByRecruiterId(Long recruiterId) {
		return jobApplicationDao.findJobSeekersById(recruiterId);
	}

}