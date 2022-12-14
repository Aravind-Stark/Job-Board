package com.example.jobboardapp.service;

import java.util.List;

import com.example.jobboardapp.dto.RecruiterRegisterDTO;
import com.example.jobboardapp.dto.UserLoginDTO;
import org.springframework.stereotype.Service;

import com.example.jobboardapp.dto.RecruiterDTO;
import com.example.jobboardapp.dto.RecruiterListDTO;
import com.example.jobboardapp.entities.Recruiter;

@Service
public interface IRecruiterService {

	RecruiterDTO findById(Long id);

	Long getCurrentId();

	Recruiter save(RecruiterRegisterDTO recruiterRegisterDTO);

	Recruiter update(Long id, RecruiterRegisterDTO recruiterRegisterDTO);

    String recruiterLogin(UserLoginDTO userLoginDTO);

    /*List<RecruiterDTO> findAll();*/
}
