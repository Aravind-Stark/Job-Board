package com.example.jobboardapp.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
public class JobSeeker implements users {
	@Id
	@Column(name = "jobseeker_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;

	@Pattern(regexp="^[a-zA-Z0-9]{6,10}",message="length must be between 6 to 20")
	@Column(nullable = false)
	private String password;
	@Email
	@Column(nullable = false)
	private String email;

	private String skill;

	@OneToOne(targetEntity=Attachment.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH})
	@JoinColumn(name = "attachment_id")
	private Attachment attachment;

	@OneToMany(mappedBy = "jobSeeker",targetEntity = JobApplication.class,cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	private List<JobApplication> appliedJobs;

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}


	public JobSeeker() {
		super();
	}

	public JobSeeker(Long id, String firstName, String lastName, String password, String email,String skill) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.skill=skill;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<JobApplication> getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(List<JobApplication> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}


}
