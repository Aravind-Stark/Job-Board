package com.example.jobboardapp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
public class Recruiter implements Users {


	@Id
	@Column(name = "recruiter_id",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(nullable = false)
	private String firstName;
	private String lastName;
	@Email
	@Column(unique = true, nullable = false)
	private String email;

	@Pattern(regexp="^[a-zA-Z0-9]{6,10}",message="length must be between 6 to 20")
	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "postedBy", targetEntity = Job.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH, CascadeType.DETACH })
	private List<Job> postedJobs;

	@OneToMany(mappedBy = "recruiter", targetEntity = JobApplication.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH, CascadeType.DETACH })
	private List<JobApplication> jobApplications;

	private String companyName;

	public Recruiter() {
		super();
	}

	public Recruiter(String email, String firstName, String lastName, List<Job> postedJobs, String password,String companyName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.postedJobs = postedJobs;
		this.password = password;
		this.companyName = companyName;
	}

	public Recruiter(String firstName, String lastName, String email, String password, String companyName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Job> getPostedJobs() {
		return postedJobs;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPostedJobs(List<Job> postedJobs) {
		this.postedJobs = postedJobs;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<JobApplication> getJobApplications() {
		return jobApplications;
	}

	public void setJobApplications(List<JobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
