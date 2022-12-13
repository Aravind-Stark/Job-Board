package com.example.jobboardapp.entities;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"job_app_id", "jobseeker_id"}))
public class JobApplication {

	@Id
	@Column(name = "job_app_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "job_app_seq")
	@SequenceGenerator(name = "job_app_seq", sequenceName = "job_app_seq", allocationSize = 1)
	private Long id;

	@ManyToOne(targetEntity = Job.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "job_id")
	private Job job;

	@ManyToOne(targetEntity = Recruiter.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;
	
	@ManyToOne(targetEntity = JobSeeker.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name="jobseeker_id")
	private JobSeeker jobSeeker;

	private LocalDateTime appliedDate = LocalDateTime.now();

	private String status;


	public JobApplication() {
		super();
	}

	public JobApplication(Job job, LocalDateTime appliedDate, String status) {
		super();
		this.job = job;
		this.appliedDate = appliedDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public LocalDateTime getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDateTime appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
