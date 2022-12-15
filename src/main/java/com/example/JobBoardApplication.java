package com.example;

import com.example.jobboardapp.dao.IJobSeekerDao;
import com.example.jobboardapp.dao.IRecruiterDao;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.entities.Recruiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JobBoardApplication {

	@Autowired
	IJobSeekerDao jobSeekerDao;

	@Autowired
	IRecruiterDao recruiterDao;

	/*@PostConstruct
	public void initUsers() {
		List<JobSeeker> jobSeeker = Stream.of(
				new JobSeeker(1L, "abc", "abc","123456", "javatechie@gmail.com","java"),
				new JobSeeker(2L, "aravind", "stark","123456", "arvind@gmail.com","java")
		).collect(Collectors.toList());
		jobSeekerDao.saveAll(jobSeeker);*/
	@PostConstruct
	public void initUsers() {
		List<Recruiter> recruiters = Stream.of(
				new Recruiter("abc", "abc", "abc@gmail.com","123456","capgemini"),
				new Recruiter( "aravind", "stark","arvind@gmail.com","123456","TCS")
		).collect(Collectors.toList());
		recruiterDao.saveAll(recruiters);

		List<JobSeeker> jobSeeker = Stream.of(
				new JobSeeker(1L, "abc", "abc","123456", "abc@gmail.com","java"),
				new JobSeeker(2L, "aravind", "stark","123456", "arvind@gmail.com","java")
		).collect(Collectors.toList());
		jobSeekerDao.saveAll(jobSeeker);
	}
	public static void main(String[] args) {
		SpringApplication.run(JobBoardApplication.class, args);
	}

}
