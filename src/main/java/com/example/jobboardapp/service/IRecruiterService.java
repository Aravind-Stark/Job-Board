package com.example.jobboardapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jobboardapp.dto.RecruiterDTO;
import com.example.jobboardapp.dto.RecruiterListDTO;
import com.example.jobboardapp.entities.Recruiter;

@Service
public interface IRecruiterService {

	RecruiterDTO findById(Long id);

	Long getCurrentId();

	Recruiter save(Recruiter recruiter);

	Recruiter update(Long id, Recruiter recruiter);
	
	/*List<RecruiterDTO> findAll();*/
}
