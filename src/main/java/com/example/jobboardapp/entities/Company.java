package com.example.jobboardapp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Table
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyId", unique = true, nullable = false)
    private long companyId;

    @NotEmpty
    @Column(name="companyName")
    private String companyName;

    @Column(name="websiteUrl")
    private String websiteUrl;


    public Company() {
    }

    public Company(int companyId, String companyName, String websiteUrl) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.websiteUrl = websiteUrl;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
