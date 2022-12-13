package com.example.jobboardapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobSeekerDetailsDTO {

    @JsonProperty("jobApplicationId")
    private Long id;

    private String fullName;

    private String email;

    private Long jobId;

    private String jobTitle;

    private String companyName;

  /*  private String mobileNumber;*/

    public JobSeekerDetailsDTO() {
    }

    public JobSeekerDetailsDTO(Long id, String fullName, String email, Long jobId, String jobTitle, String companyName) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
