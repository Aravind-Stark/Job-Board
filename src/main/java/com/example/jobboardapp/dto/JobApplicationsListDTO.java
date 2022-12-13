package com.example.jobboardapp.dto;

public class JobApplicationsListDTO {
	private Long id;

	private Long jobId;

	private String jobTitle;

	private Long jobSeekerId;

	private String status;

	private String companyName;



	public JobApplicationsListDTO() {
	}

	public JobApplicationsListDTO(Long id, Long jobId, String jobTitle, Long jobSeekerId, String status,String companyName) {
		this.id = id;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.jobSeekerId = jobSeekerId;
		this.status = status;
		this.companyName=companyName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(Long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
