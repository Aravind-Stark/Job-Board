package com.example.jobboardapp.dto;

import javax.validation.constraints.NotEmpty;

public class RecruiterDTO {

	private Long id;
	@NotEmpty(message = "userName cannot be empty")
	private String email;
	@NotEmpty(message = "first name cannot be empty")
	private String firstName;
	@NotEmpty(message = "last name cannot be empty")
	private String lastName;

	private  String companyName;

	public RecruiterDTO() {
		super();
	}

	public RecruiterDTO(Long id, String email, String firstName, String lastName, String companyName) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
