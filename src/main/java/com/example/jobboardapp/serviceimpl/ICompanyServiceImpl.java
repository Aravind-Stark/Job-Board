package com.example.jobboardapp.serviceimpl;

import com.example.jobboardapp.dao.ICompanyDao;
import com.example.jobboardapp.entities.Company;
import com.example.jobboardapp.exceptions.InvalidCompanyException;
import com.example.jobboardapp.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICompanyServiceImpl implements ICompanyService {

    @Autowired
    ICompanyDao companyDao;

    @Override
    public Company createCompany(Company company) {

        if(company.getCompanyName() != null && company.getWebsiteUrl()!=null){
            return companyDao.save(company);
        }
        else
        {
            throw new InvalidCompanyException();
        }
}

    @Override
    public Company updateCompany(Company company) {

        if (companyDao.existsById(company.getCompanyId())) {
            return companyDao.save(company);
        }
        else {
            throw new InvalidCompanyException();
        }
    }


}
