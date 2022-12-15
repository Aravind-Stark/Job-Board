package com.example.jobboardapp.dto;

public class JobApplicationStatusDTO {
   private   String status;

    public JobApplicationStatusDTO() {
    }

    public JobApplicationStatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
