package com.example.jobboardapp.dao;

import java.util.List;

import com.example.jobboardapp.dto.JobSeekerDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jobboardapp.dto.JobApplicationsListDTO;
import com.example.jobboardapp.entities.JobApplication;

@Repository
public interface IJobApplicationDao extends JpaRepository<JobApplication, Long> {
	
/*	@Query("select new com.cg.freelanceapp.dto.JobApplicationsListDTO(j.id, j.job.id, j.job.jobTitle, j.coverLetter, j.freelancer.id, CONCAT(j.freelancer.firstName,' ', j.freelancer.lastName), j.freelancer.userName)  from JobApplication j, BookmarkedFreelancer bf where j.job.id=:jobId and j.freelancer.id = bf.freelancer.id order by j.id")
	List<JobApplicationsListDTO> findAllByJobId(@Param("jobId") Long jobId);
	
	@Query("select new com.cg.freelanceapp.dto.JobApplicationsListDTO(j.id, j.job.id, j.job.jobTitle, j.coverLetter, f.id, CONCAT(f.firstName,' ', f.lastName), f.userName)  from JobApplication j, Freelancer f where j.freelancer = f order by j.id")
	List<JobApplicationsListDTO> findAllApps();*/
	
	//@Query("select * from(select new com.example.jobboardapp.dto.JobApplicationsListDTO(j.id, j.job.id, j.job.jobTitle, f.id, j.status)  from JobApplication j, job jb where j.job.id = :jobId) a where j.jobSeekerId.id=:jobSeekerId")
	//List<JobApplication> findByJobSeekerId(@Param("jobSeekerId") Long jobSeekerId);

	@Query("select new com.example.jobboardapp.dto.JobApplicationsListDTO(j.id, jb.id, jb.jobTitle, j.jobSeeker.id,j.status,r.companyName) from JobApplication j, Job jb, Recruiter r where j.jobSeeker.id=:jobSeekerId")
	List<JobApplicationsListDTO> findJobSeekersApplications(@Param("jobSeekerId") Long jobSeekerId);

	@Query("select new com.example.jobboardapp.dto.JobSeekerDetailsDTO(j.id, CONCAT(e.firstName,' ', e.lastName),e.email, jb.id, jb.jobTitle, jb.companyName ) from JobApplication j, JobSeeker e, Job jb  where j.job.id = jb.id and j.jobSeeker.id = e.id and j.recruiter.id = :recruiterId")
	List<JobSeekerDetailsDTO> findJobSeekersById(@Param("recruiterId") Long recruiterId);

	@Query("select j.jobSeeker.id from JobApplication j where j.jobSeeker.id = :jobSeekerId and j.job.id = :jobId")
	Long findByJobSeekerIdAndJobId(@Param("jobSeekerId") Long jobSeekerId,@Param("jobId") Long jobId);

    //List<JobApplicationsListDTO> findByJobSeekerId(@Param("jobId") Long jobId, @Param("frId") Long frId);
}
