package com.example.jobboardapp.dao;

import java.util.List;
import java.util.Optional;

import com.example.jobboardapp.dto.JobListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jobboardapp.entities.Job;

@Repository
public interface IJobDao extends JpaRepository<Job, Long> {

	/*@Query("SELECT new com.example.jobboardapp.dto.JobListDTO(j.id, j.awardedTo.id, CONCAT(j.awardedTo.firstName,' ',j.awardedTo.lastName) as jobSeekerName, j.skill.id, j.skill.name, j.postedBy.id, CONCAT(j.postedBy.firstName,' ',j.postedBy.lastName) as recruiterName, j.jobTitle, j.jobDescription, j.active) from Job j order by j.id")
	public List<Object> findAllDTO();*/
	
	/*@Query("SELECT new com.example.jobboardapp.dto.JobListDTO(j.id, j.awardedTo.id, CONCAT(j.awardedTo.firstName,' ',j.awardedTo.lastName) as jobSeekerName, j.skill.id, j.skill.name, j.postedBy.id,CONCAT(j.postedBy.firstName,' ',j.postedBy.lastName) as recruiterName, j.jobTitle, j.jobDescription, j.active) from Job j where j.active = true order by j.id")
	public List<Object> findAllActiveDTO();*/


	/*@Query("SELECT new com.example.jobboardapp.dto.JobListDTO(j.id, j.jobTitle, j.jobDescription, j.skill,j.postedBy.id,j.location,postedDate) from Job j where j.skill LIKE %:skill% order by j.id")
	public List<JobListDTO> findBySkill(@Param("skill") String skill);*/

    public List<Job> findBySkillContainingIgnoreCase(@Param("skill") String skill);

  /*  @Query("SELECT new com.example.jobboardapp.dto.JobListDTO(j.id, j.jobTitle, j.jobDescription, j.skill,j.postedBy.id,j.location,postedDate) from Job j where j.skill LIKE %:title% order by j.id")
    public List<JobListDTO> findByTitle(@Param("skill") String title);*/

    public List<Job> findByJobTitleIgnoreCase(String title);

    @Query("SELECT new com.example.jobboardapp.dto.JobListDTO(j.id, j.jobTitle, j.jobDescription, j.skill,j.postedBy.id,j.location,j.postedDate,j.companyName) from Job j where j.postedBy.id=:recruiterId order by j.id")
    public List<JobListDTO> findByRecruiterId(@Param("recruiterId") Long recruiterId);

    List<Job> findByLocationIgnoreCase(String location);
}
