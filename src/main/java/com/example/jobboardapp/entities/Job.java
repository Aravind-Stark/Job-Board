package com.example.jobboardapp.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Job  {

	@Id
	@Column(name = "job_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "job_seq")
	@SequenceGenerator(name = "job_seq", sequenceName = "job_seq", allocationSize = 1)
	private Long id;

	private String jobTitle;

	private String jobDescription;


	@ManyToOne(targetEntity = Recruiter.class, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "recruiter_id")
	private Recruiter postedBy;

	@CreationTimestamp
	private LocalDateTime postedDate;
	//private LocalDate postedDate = LocalDate.now(ZoneId.of("GMT+05:30"));

	/*@OneToOne(targetEntity = JobSeeker.class, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private JobSeeker awardedTo;*/

	@OneToMany(mappedBy = "job", targetEntity = JobApplication.class, cascade = { CascadeType.MERGE,
			CascadeType.REFRESH })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<JobApplication> jobApplications;

	private String location;
	private String skill;

	private String companyName;

	public Job() {
		super();
	}

	public Job(Long id, String jobTitle, String jobDescription, Recruiter postedBy, LocalDateTime postedDate, List<JobApplication> jobApplications, String skill, String location, String companyName) {
		this.id = id;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.postedBy = postedBy;
		this.postedDate = postedDate;
		this.jobApplications = jobApplications;
		this.skill = skill;
		this.location = location;
		this.companyName = companyName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Recruiter getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(Recruiter postedBy) {
		this.postedBy = postedBy;
	}

	public LocalDateTime getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDateTime postedDate) {
		this.postedDate = postedDate;
	}


	public List<JobApplication> getJobApplications() {
		return jobApplications;
	}

	public void setJobApplications(List<JobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
