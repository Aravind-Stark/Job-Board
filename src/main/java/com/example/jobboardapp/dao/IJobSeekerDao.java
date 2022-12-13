package com.example.jobboardapp.dao;

import java.util.List;

import com.example.jobboardapp.dto.JobSeekerDTO;
import com.example.jobboardapp.entities.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobSeekerDao extends JpaRepository<JobSeeker, Long> {

	/*******************************************************************************************
	 * Method:      getCurrentSeriesId
	 * @param       none
	 * @return      Long
	 * Description: This method returns the current value of primary key from the sequence.
	 *******************************************************************************************/
	@Query(value = "select freelancer_seq.currval from dual", nativeQuery = true)
	Long getCurrentSeriesId();

	@Query(value = "select new com.example.jobboardapp.dto.JobSeekerDTO(f.id, f.firstName, f.lastName, f.email) from JobSeeker f where not f.firstName like 'dummy%' order by f.id")
	public List<JobSeekerDTO> findAllJobSeeker();

    List<JobSeeker> findBySkillContainingIgnoreCase(@Param("skill") String skill);
}
