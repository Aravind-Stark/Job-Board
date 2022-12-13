package com.example.jobboardapp.dao;

import com.example.jobboardapp.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyDao extends JpaRepository<Company,Long> {
}
