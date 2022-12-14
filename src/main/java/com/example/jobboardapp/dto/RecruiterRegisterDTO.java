package com.example.jobboardapp.dto;

import com.example.jobboardapp.entities.register;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class RecruiterRegisterDTO implements register {

    private String firstName;
    @NotEmpty(message = "lastName cannot be empty")
    private String lastName;
    @Email
    @Column(nullable = false)
    private String email;
    @Pattern(regexp="^[a-zA-Z0-9]{6,10}",message="length must be between 6 to 20")
    private String password;
    private String companyName;

    public RecruiterRegisterDTO() {
    }

    public RecruiterRegisterDTO(String firstName, String lastName, String email, String password, String companyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
