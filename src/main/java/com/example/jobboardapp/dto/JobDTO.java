package com.example.jobboardapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class JobDTO {

	private long jobId = 2L;
	private long jobSeekerId = 2L;
	@NotNull(message = "recruiterid cant be null")
	private long recruiterId;
	private String jobTitle;
	private String jobDescription;
	private String location;
	private String skill;
	private LocalDateTime postedDate;
	@NotEmpty
	private String companyName;

	public JobDTO() {
		super();
	}

	public JobDTO(long jobId, long jobSeekerId, long recruiterId, String jobTitle, String jobDescription, String location, String skill, LocalDateTime postedDate, String companyName) {
		this.jobId = jobId;
		this.jobSeekerId = jobSeekerId;
		this.recruiterId = recruiterId;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.location = location;
		this.skill = skill;
		this.postedDate = postedDate;
		this.companyName = companyName;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public long getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public long getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(long recruiterId) {
		this.recruiterId = recruiterId;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
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