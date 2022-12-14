package com.example.jobboardapp.serviceimpl;

import com.example.jobboardapp.dao.IRecruiterDao;
import com.example.jobboardapp.dto.RecruiterDTO;
import com.example.jobboardapp.dto.RecruiterRegisterDTO;
import com.example.jobboardapp.dto.UserLoginDTO;
import com.example.jobboardapp.entities.Recruiter;
import com.example.jobboardapp.exceptions.InvalidRecruiterException;
import com.example.jobboardapp.service.IRecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecruiterServiceImpl implements IRecruiterService {

	@Autowired
	IRecruiterDao recruiterDao;

	@Override
	public RecruiterDTO findById(Long id) {
		Recruiter recruiter;
		RecruiterDTO recruiterDTO = new RecruiterDTO();
		if (recruiterDao.existsById(id)) {
			recruiter = recruiterDao.findById(id).get();
		} else
			throw new InvalidRecruiterException();
		recruiterDTO.setId(recruiter.getId());
		recruiterDTO.setFirstName(recruiter.getFirstName());
		recruiterDTO.setLastName(recruiter.getLastName());
		recruiterDTO.setEmail(recruiter.getEmail());
		return recruiterDTO;

	}

	/*******************************************************************************************
	 * Method:      getCurrentSeriesId
	 * @param       none
	 * @return      Long
	 * Description: This method returns the current value of primary key from the sequence.
	 *******************************************************************************************/
	@Override
	public Long getCurrentId() {
		return recruiterDao.getCurrentSeriesId();
	}

	@Override
	public Recruiter save(RecruiterRegisterDTO recruiterRegisterDTO) {
		if (!(recruiterRegisterDTO.getFirstName() == null || recruiterRegisterDTO.getLastName() == null
				|| recruiterRegisterDTO.getEmail() == null || recruiterRegisterDTO.getPassword() == null)) {
			Recruiter recruiter = new Recruiter();
            recruiter.setFirstName(recruiterRegisterDTO.getFirstName());
			recruiter.setLastName(recruiterRegisterDTO.getLastName());
			recruiter.setEmail(recruiterRegisterDTO.getEmail());
			recruiter.setPassword(recruiterRegisterDTO.getPassword());
			recruiter.setCompanyName(recruiterRegisterDTO.getCompanyName());
			return recruiterDao.save(recruiter);
		}
		else
			throw new InvalidRecruiterException();
	}

	@Override
	public Recruiter update(Long id, RecruiterRegisterDTO recruiterRegisterDTO) {
		if (recruiterDao.existsById(id)) {
			Recruiter recruiter = new Recruiter();
			recruiter.setFirstName(recruiterRegisterDTO.getFirstName());
			recruiter.setLastName(recruiterRegisterDTO.getLastName());
			recruiter.setEmail(recruiterRegisterDTO.getEmail());
			recruiter.setPassword(recruiterRegisterDTO.getPassword());
			recruiter.setCompanyName(recruiterRegisterDTO.getCompanyName());
			return recruiterDao.save(recruiter);
		} else
			throw new InvalidRecruiterException("Invalid recruiter id");
	}

	@Override
	public String recruiterLogin(UserLoginDTO userLoginDTO) {
		Recruiter recruiter= recruiterDao.findByEmail(userLoginDTO.getEmail());
		if(recruiter!=null && recruiter.getEmail().equals(userLoginDTO.getEmail()) &&recruiter.getPassword().equals(userLoginDTO.getPassword()) ){
			return "Logged in succesfully";}
		else {
			throw  new InvalidRecruiterException("Invalid credentials");
		}
	}

/*
	@Override
	public List<RecruiterDTO> findAll(){
		return recruiterDao.findAllRecruiters();
	}*/
}
