package com.example.jobboardapp.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class JobSeekerRegisterDTO {
    @NotEmpty(message = "firstName cannot be empty")
    private String firstName;
    @NotEmpty(message = "lastName cannot be empty")
    private String lastName;
    @Email
    @Column(nullable = false)
    private String email;

    private String password;

    private String skill;

    public JobSeekerRegisterDTO() {
    }

    public JobSeekerRegisterDTO(String firstName, String lastName, String email, String password, String skill) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.skill = skill;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
