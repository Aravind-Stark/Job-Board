package com.example.jobboardapp.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class JobSeekerDTO {

	private Long id;
	@NotEmpty(message = "firstName cannot be empty")
	private String firstName;
	@NotEmpty(message = "lastName cannot be empty")
	private String lastName;
	@Email
	@Column(nullable = false)
	private String email;



	public JobSeekerDTO() {
		super();
	}

	public JobSeekerDTO(Long id, String firstName, String lastName,String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
