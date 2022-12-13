package com.example.jobboardapp.serviceimpl;

import com.example.jobboardapp.dao.IJobSeekerDao;
import com.example.jobboardapp.dto.JobListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobboardapp.dao.IJobDao;
import com.example.jobboardapp.dao.IRecruiterDao;
import com.example.jobboardapp.dto.JobDTO;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.entities.Job;
import com.example.jobboardapp.exceptions.InvalidJobException;
import com.example.jobboardapp.service.IJobService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class JobServiceImpl implements IJobService {

	@Autowired
	IJobDao jobdao;

	@Autowired
	IJobSeekerDao jobSeekerDao;

	@Autowired
	IRecruiterDao recruiterDao;

	public void close(Long id) {
		if (jobdao.existsById(id)) {
			Job job = jobdao.findById(id).get();
			jobdao.save(job);
		} else {
			throw new InvalidJobException();
		}
	}

	@Override
	public Job findById(Long id) {

		if (jobdao.existsById(id)) {
			return jobdao.findById(id).get();
		} else
			throw new InvalidJobException();

	}

	@Override
	public List<JobListDTO> findJobsBySkill(String name) {
		List<Job> job = jobdao.findBySkillContainingIgnoreCase(name);
		List<JobListDTO> jobListDTOS = new ArrayList<>();
		System.out.println("job size "+job.size());
		for(int i=0;i<job.size();i++){
			JobListDTO jobListDTO = new JobListDTO();
			jobListDTO.setJobId(job.get(i).getId());
			jobListDTO.setJobTitle(job.get(i).getJobTitle());
			jobListDTO.setJobDescription(job.get(i).getJobDescription());
			jobListDTO.setSkill(job.get(i).getSkill());
			jobListDTO.setLocation(job.get(i).getLocation());
			jobListDTO.setPostedDate(job.get(i).getPostedDate());
			jobListDTO.setRecruiterId(job.get(i).getPostedBy().getId());
			jobListDTOS.add(jobListDTO);
		}

		return jobListDTOS;
	}

	@Override
	public Job postJob(JobDTO jobdto) {
		Job job = new Job();
		if (recruiterDao.existsById(jobdto.getRecruiterId())) {
			job.setPostedBy(recruiterDao.findById(jobdto.getRecruiterId()).get());
			//job.setAwardedTo(jobSeekerDao.findById(2L).get());
			job.setJobTitle(jobdto.getJobTitle());
			job.setJobDescription(jobdto.getJobDescription());
			job.setLocation(jobdto.getLocation());
			job.setSkill(jobdto.getSkill());
			job.setPostedDate(jobdto.getPostedDate());
			return jobdao.save(job);
		} else
			throw new InvalidJobException();
		//return jobdao.save(job);
	}

	@Override
	public List<JobListDTO> findAll() {
		List<Job> job =jobdao.findAll();

		List<JobListDTO> jobListDTOS = new ArrayList<>();
		System.out.println("job size "+job.size());
		for(int i=0;i<job.size();i++){
			JobListDTO jobListDTO = new JobListDTO();
			jobListDTO.setJobId(job.get(i).getId());
			jobListDTO.setJobTitle(job.get(i).getJobTitle());
			jobListDTO.setJobDescription(job.get(i).getJobDescription());
			jobListDTO.setSkill(job.get(i).getSkill());
			jobListDTO.setLocation(job.get(i).getLocation());
			jobListDTO.setPostedDate(job.get(i).getPostedDate());
			jobListDTO.setRecruiterId(job.get(i).getPostedBy().getId());
			jobListDTOS.add(jobListDTO);
		}


		return jobListDTOS;
	}

	@Override
	public void awardJob(Long jobId, Long jobSeekerId) {
		Job job = jobdao.findById(jobId).get();
		JobSeeker jobSeeker = jobSeekerDao.findById(jobSeekerId).get();
		jobdao.saveAndFlush(job);
		
	}

	@Override
	public Job updateJob(JobDTO jobDto) {
		Optional<Job> job = jobdao.findById(jobDto.getJobId());
		if (recruiterDao.existsById(jobDto.getRecruiterId()) && jobdao.existsById(jobDto.getJobId())) {
			job.get().setJobTitle(jobDto.getJobTitle()!=""?jobDto.getJobTitle():job.get().getJobTitle());
			job.get().setJobDescription(jobDto.getJobDescription()!=""?jobDto.getJobDescription():job.get().getJobDescription());
			job.get().setLocation(jobDto.getLocation()!=""?jobDto.getLocation():job.get().getLocation());
			job.get().setSkill(jobDto.getSkill()!=""?jobDto.getSkill():job.get().getSkill());
			job.get().setCompanyName(jobDto.getCompanyName()!=""?jobDto.getCompanyName():job.get().getCompanyName());
			/*job.setJobTitle(jobDto.getJobTitle());
			job.setJobDescription(jobDto.getJobDescription());
			job.setLocation(jobDto.getLocation());
			job.setSkill(jobDto.getSkill());*/
			return jobdao.save(job.get());
		} else
			throw new InvalidJobException();
	}

	@Override
	public void deleteJob(Long id) {
		Optional<Job> job = jobdao.findById(id);
		if(job.isPresent()){
			jobdao.delete(job.get());
		}
		else
			throw new InvalidJobException();
	}

	@Override
	public List<JobListDTO> findByRecruiterId(Long id) {
		Long recruiterId = id;
		if(recruiterDao.existsById(id)){

			return jobdao.findByRecruiterId(recruiterId);
		}else
			throw new InvalidJobException();
	}

	@Override
	public List<JobListDTO> findJobsByTitle(String title) {
		List<Job> job =jobdao.findByJobTitleIgnoreCase(title);

		List<JobListDTO> jobListDTOS = new ArrayList<>();
		System.out.println("job size "+job.size());
		for(int i=0;i<job.size();i++){
			JobListDTO jobListDTO = new JobListDTO();
			jobListDTO.setJobId(job.get(i).getId());
			jobListDTO.setJobTitle(job.get(i).getJobTitle());
			jobListDTO.setJobDescription(job.get(i).getJobDescription());
			jobListDTO.setSkill(job.get(i).getSkill());
			jobListDTO.setLocation(job.get(i).getLocation());
			jobListDTO.setPostedDate(job.get(i).getPostedDate());
			jobListDTO.setRecruiterId(job.get(i).getPostedBy().getId());
			jobListDTOS.add(jobListDTO);
		}

		return jobListDTOS;
	}

	@Override
	public List<JobListDTO> findJobsByLocation(String location) {
		List<Job> job =jobdao.findByLocationIgnoreCase(location);

		List<JobListDTO> jobListDTOS = new ArrayList<>();
		System.out.println("job size "+job.size());
		for(int i=0;i<job.size();i++){
			JobListDTO jobListDTO = new JobListDTO();
			jobListDTO.setJobId(job.get(i).getId());
			jobListDTO.setJobTitle(job.get(i).getJobTitle());
			jobListDTO.setJobDescription(job.get(i).getJobDescription());
			jobListDTO.setSkill(job.get(i).getSkill());
			jobListDTO.setLocation(job.get(i).getLocation());
			jobListDTO.setPostedDate(job.get(i).getPostedDate());
			jobListDTO.setRecruiterId(job.get(i).getPostedBy().getId());
			jobListDTOS.add(jobListDTO);
		}

		return jobListDTOS;
	}

	/*@Override
	public List<Object> findAllActiveJobs() {
		return jobdao.findAllActiveDTO();
	}*/

}