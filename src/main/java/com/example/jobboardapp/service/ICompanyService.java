package com.example.jobboardapp.service;

import com.example.jobboardapp.entities.Company;

public interface ICompanyService {
    Company createCompany(Company company);

    Company updateCompany(Company company);
}
