package com.example.jobboardapp.dto;

import java.time.LocalDateTime;

public class JobListDTO {

	private Long jobId;

	private String jobTitle;

	private String jobDescription;

	private String skill;
	private Long recruiterId;

	private String location;

	private LocalDateTime postedDate;

	private String companyName;

	public JobListDTO() {
	}

	public JobListDTO(Long jobId, String jobTitle, String jobDescription, String skill, Long recruiterId, String location, LocalDateTime postedDate, String companyName) {
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.skill = skill;
		this.recruiterId = recruiterId;
		this.location = location;
		this.postedDate = postedDate;
		this.companyName = companyName;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Long getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(Long recruiterId) {
		this.recruiterId = recruiterId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDateTime postedDate) {
		this.postedDate = postedDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
