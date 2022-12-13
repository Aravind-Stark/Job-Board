package com.example.jobboardapp.controller;

import com.example.jobboardapp.entities.Company;
import com.example.jobboardapp.exceptions.InvalidCompanyException;
import com.example.jobboardapp.exceptions.InvalidJobSeekerException;
import com.example.jobboardapp.service.ICompanyService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CompanyController {
    private static final Logger logger = Logger.getLogger(CompanyController.class);
    @Autowired
    ICompanyService companyService;

    @GetMapping("/hello")
    public String dayHello()
    {
        return "Hello Aravindhan";
    }

    @PostMapping("/createCompany")
    public ResponseEntity<Object> createCompany(@RequestBody Company company) {
        logger.info(company.getCompanyId());

        try {
            companyService.createCompany(company);
        } catch (InvalidCompanyException exception) {
            throw new InvalidCompanyException("One or more entered fields contain invalid objects.");
        }
        return new ResponseEntity<>("company created Successfully", HttpStatus.OK);
    }

    @PostMapping("/updateCompany")
    public ResponseEntity<Object> updateCompany(@RequestBody Company company) {
        logger.info(company.getCompanyId());

        try {
            companyService.updateCompany(company);
        } catch (InvalidCompanyException exception) {
            throw new InvalidCompanyException("One or more entered fields contain invalid objects.");
        }
        return new ResponseEntity<>("company details updated Successfully", HttpStatus.OK);
    }



}
