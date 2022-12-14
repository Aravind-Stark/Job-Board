package com.example.jobboardapp.dto;

import com.example.jobboardapp.entities.Users;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginDTO implements Users {

    @JsonProperty("userName")
    private String email ;
    private String password ;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
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
}
