package com.example.jobboardapp.dao;

import java.util.List;

import com.example.jobboardapp.dto.RecruiterDTO;
import com.example.jobboardapp.entities.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jobboardapp.dto.RecruiterListDTO;
import com.example.jobboardapp.entities.Recruiter;

@Repository
public interface IRecruiterDao extends JpaRepository<Recruiter, Long> {

	/*******************************************************************************************
	 * Method:      getCurrentSeriesId
	 * @param       none
	 * @return      Long
	 * Description: This method returns the current value of primary key from the sequence.
	 *******************************************************************************************/
	@Query(value = "select recruiter_seq.currval from dual", nativeQuery = true)
	Long getCurrentSeriesId();

	/*public boolean existsByUserName(String userName);*/
	
	/*@Query("select new com.cg.freelanceapp.dto.RecruiterDTO(r.id, r.userName, r.firstName, r.lastName, r.password) from Recruiter r")
	public List<RecruiterDTO> findAllRecruiters();*/

	Recruiter findByEmail(@Param("email")String email);
}
