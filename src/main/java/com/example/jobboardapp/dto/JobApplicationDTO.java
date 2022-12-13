package com.example.jobboardapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class JobApplicationDTO {
	@NotNull(message = "Job ID cannot be blank")
	private Long jobId;
	//@NotEmpty(message = "Cover Letter cannot be empty")
	private String status;
	@NotNull
	private Long recruiterId;
	@NotNull
	private Long jobseekerId;


	public JobApplicationDTO() {
		super();
	}

	public JobApplicationDTO(Long jobId, String status, Long recruiterId, Long jobseekerId) {
		this.jobId = jobId;
		this.status = status;
		this.recruiterId = recruiterId;
		this.jobseekerId = jobseekerId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(Long recruiterId) {
		this.recruiterId = recruiterId;
	}

	public Long getJobseekerId() {
		return jobseekerId;
	}

	public void setJobseekerId(Long jobseekerId) {
		this.jobseekerId = jobseekerId;
	}

}
