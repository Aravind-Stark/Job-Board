package com.example.jobboardapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jobboardapp.dao.IJobApplicationDao;
import com.example.jobboardapp.dao.IJobDao;
import com.example.jobboardapp.dao.IJobSeekerDao;
import com.example.jobboardapp.dao.IRecruiterDao;
import com.example.jobboardapp.dto.JobApplicationDTO;
import com.example.jobboardapp.dto.JobApplicationsListDTO;
import com.example.jobboardapp.dto.JobSeekerDetailsDTO;
import com.example.jobboardapp.entities.Attachment;
import com.example.jobboardapp.entities.Job;
import com.example.jobboardapp.entities.JobApplication;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.entities.Recruiter;
import com.example.jobboardapp.exceptions.InvalidJobApplicationException;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JobApplicationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class JobApplicationServiceImplTest {
    @MockBean
    private IJobApplicationDao iJobApplicationDao;

    @MockBean
    private IJobDao iJobDao;

    @MockBean
    private IJobSeekerDao iJobSeekerDao;

    @MockBean
    private IRecruiterDao iRecruiterDao;

    @Autowired
    private JobApplicationServiceImpl jobApplicationServiceImpl;

    /**
     * Method under test: {@link JobApplicationServiceImpl#applyToJob(JobApplicationDTO)}
     */
    @Test
    void testApplyToJob() {
        when(iJobApplicationDao.findByJobSeekerIdAndJobId((Long) any(), (Long) any())).thenReturn(123L);
        when(iJobDao.existsById((Long) any())).thenReturn(true);
        assertThrows(InvalidJobApplicationException.class,
                () -> jobApplicationServiceImpl.applyToJob(new JobApplicationDTO()));
        verify(iJobApplicationDao).findByJobSeekerIdAndJobId((Long) any(), (Long) any());
        verify(iJobDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#applyToJob(JobApplicationDTO)}
     */
    @Test
    void testApplyToJob2() {
        when(iJobApplicationDao.findByJobSeekerIdAndJobId((Long) any(), (Long) any())).thenReturn(123L);
        when(iJobDao.existsById((Long) any())).thenThrow(new InvalidJobApplicationException("An error occurred"));
        assertThrows(InvalidJobApplicationException.class,
                () -> jobApplicationServiceImpl.applyToJob(new JobApplicationDTO()));
        verify(iJobApplicationDao).findByJobSeekerIdAndJobId((Long) any(), (Long) any());
        verify(iJobDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#applyToJob(JobApplicationDTO)}
     */
    @Test
    void testApplyToJob3() {
        when(iJobApplicationDao.findByJobSeekerIdAndJobId((Long) any(), (Long) any())).thenReturn(null);
        when(iJobDao.existsById((Long) any())).thenReturn(true);
        assertThrows(InvalidJobApplicationException.class,
                () -> jobApplicationServiceImpl.applyToJob(new JobApplicationDTO()));
        verify(iJobApplicationDao).findByJobSeekerIdAndJobId((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#applyToJob(JobApplicationDTO)}
     */
    @Test
    void testApplyToJob4() {
        when(iJobApplicationDao.findByJobSeekerIdAndJobId((Long) any(), (Long) any())).thenReturn(123L);
        when(iJobDao.existsById((Long) any())).thenReturn(false);
        assertThrows(InvalidJobApplicationException.class,
                () -> jobApplicationServiceImpl.applyToJob(new JobApplicationDTO()));
        verify(iJobApplicationDao).findByJobSeekerIdAndJobId((Long) any(), (Long) any());
        verify(iJobDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#applyToJob(JobApplicationDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testApplyToJob5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.jobboardapp.serviceimpl.JobApplicationServiceImpl.applyToJob(JobApplicationServiceImpl.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        when(iJobApplicationDao.findByJobSeekerIdAndJobId((Long) any(), (Long) any())).thenReturn(123L);
        when(iJobDao.existsById((Long) any())).thenReturn(true);
        jobApplicationServiceImpl.applyToJob(null);
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#applyToJob(JobApplicationDTO)}
     */
    @Test
    void testApplyToJob6() throws UnsupportedEncodingException {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());

        Job job = new Job();
        job.setCompanyName("Company Name");
        job.setId(123L);
        job.setJobApplications(new ArrayList<>());
        job.setJobDescription("Job Description");
        job.setJobTitle("Dr");
        job.setLocation("Location");
        job.setPostedBy(recruiter);
        job.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job.setSkill("Skill");

        Attachment attachment = new Attachment();
        attachment.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment.setFileName("foo.txt");
        attachment.setFileType("File Type");
        attachment.setId("42");
        attachment.setJobSeeker(new JobSeeker());

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setAppliedJobs(new ArrayList<>());
        jobSeeker.setAttachment(attachment);
        jobSeeker.setEmail("jane.doe@example.org");
        jobSeeker.setFirstName("Jane");
        jobSeeker.setId(123L);
        jobSeeker.setLastName("Doe");
        jobSeeker.setPassword("iloveyou");
        jobSeeker.setSkill("Skill");

        Attachment attachment1 = new Attachment();
        attachment1.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment1.setFileName("foo.txt");
        attachment1.setFileType("File Type");
        attachment1.setId("42");
        attachment1.setJobSeeker(jobSeeker);

        JobSeeker jobSeeker1 = new JobSeeker();
        jobSeeker1.setAppliedJobs(new ArrayList<>());
        jobSeeker1.setAttachment(attachment1);
        jobSeeker1.setEmail("jane.doe@example.org");
        jobSeeker1.setFirstName("Jane");
        jobSeeker1.setId(123L);
        jobSeeker1.setLastName("Doe");
        jobSeeker1.setPassword("iloveyou");
        jobSeeker1.setSkill("Skill");

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());

        JobApplication jobApplication = new JobApplication();
        jobApplication.setAppliedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        jobApplication.setId(123L);
        jobApplication.setJob(job);
        jobApplication.setJobSeeker(jobSeeker1);
        jobApplication.setRecruiter(recruiter1);
        jobApplication.setStatus("Status");
        when(iJobApplicationDao.save((JobApplication) any())).thenReturn(jobApplication);
        when(iJobApplicationDao.findByJobSeekerIdAndJobId((Long) any(), (Long) any())).thenReturn(123L);

        Recruiter recruiter2 = new Recruiter();
        recruiter2.setCompanyName("Company Name");
        recruiter2.setEmail("jane.doe@example.org");
        recruiter2.setFirstName("Jane");
        recruiter2.setId(123L);
        recruiter2.setJobApplications(new ArrayList<>());
        recruiter2.setLastName("Doe");
        recruiter2.setPassword("iloveyou");
        recruiter2.setPostedJobs(new ArrayList<>());

        Job job1 = new Job();
        job1.setCompanyName("Company Name");
        job1.setId(123L);
        job1.setJobApplications(new ArrayList<>());
        job1.setJobDescription("Job Description");
        job1.setJobTitle("Dr");
        job1.setLocation("Location");
        job1.setPostedBy(recruiter2);
        job1.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job1.setSkill("Skill");
        Optional<Job> ofResult = Optional.of(job1);
        when(iJobDao.findById((Long) any())).thenReturn(ofResult);
        when(iJobDao.existsById((Long) any())).thenReturn(true);

        Attachment attachment2 = new Attachment();
        attachment2.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment2.setFileName("foo.txt");
        attachment2.setFileType("File Type");
        attachment2.setId("42");
        attachment2.setJobSeeker(new JobSeeker());

        JobSeeker jobSeeker2 = new JobSeeker();
        jobSeeker2.setAppliedJobs(new ArrayList<>());
        jobSeeker2.setAttachment(attachment2);
        jobSeeker2.setEmail("jane.doe@example.org");
        jobSeeker2.setFirstName("Jane");
        jobSeeker2.setId(123L);
        jobSeeker2.setLastName("Doe");
        jobSeeker2.setPassword("iloveyou");
        jobSeeker2.setSkill("Skill");

        Attachment attachment3 = new Attachment();
        attachment3.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment3.setFileName("foo.txt");
        attachment3.setFileType("File Type");
        attachment3.setId("42");
        attachment3.setJobSeeker(jobSeeker2);

        JobSeeker jobSeeker3 = new JobSeeker();
        jobSeeker3.setAppliedJobs(new ArrayList<>());
        jobSeeker3.setAttachment(attachment3);
        jobSeeker3.setEmail("jane.doe@example.org");
        jobSeeker3.setFirstName("Jane");
        jobSeeker3.setId(123L);
        jobSeeker3.setLastName("Doe");
        jobSeeker3.setPassword("iloveyou");
        jobSeeker3.setSkill("Skill");
        Optional<JobSeeker> ofResult1 = Optional.of(jobSeeker3);
        when(iJobSeekerDao.findById((Long) any())).thenReturn(ofResult1);

        Recruiter recruiter3 = new Recruiter();
        recruiter3.setCompanyName("Company Name");
        recruiter3.setEmail("jane.doe@example.org");
        recruiter3.setFirstName("Jane");
        recruiter3.setId(123L);
        recruiter3.setJobApplications(new ArrayList<>());
        recruiter3.setLastName("Doe");
        recruiter3.setPassword("iloveyou");
        recruiter3.setPostedJobs(new ArrayList<>());
        Optional<Recruiter> ofResult2 = Optional.of(recruiter3);
        when(iRecruiterDao.findById((Long) any())).thenReturn(ofResult2);
        JobApplicationDTO jobApplicationDTO = mock(JobApplicationDTO.class);
        when(jobApplicationDTO.getJobId()).thenReturn(123L);
        when(jobApplicationDTO.getJobseekerId()).thenReturn(123L);
        when(jobApplicationDTO.getRecruiterId()).thenReturn(123L);
        assertThrows(InvalidJobApplicationException.class, () -> jobApplicationServiceImpl.applyToJob(jobApplicationDTO));
        verify(iJobApplicationDao).findByJobSeekerIdAndJobId((Long) any(), (Long) any());
        verify(jobApplicationDTO).getJobId();
        verify(jobApplicationDTO, atLeast(1)).getJobseekerId();
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#updateJobApplication(Long, JobApplicationDTO)}
     */
    @Test
    void testUpdateJobApplication() throws UnsupportedEncodingException {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());

        Job job = new Job();
        job.setCompanyName("Company Name");
        job.setId(123L);
        job.setJobApplications(new ArrayList<>());
        job.setJobDescription("Job Description");
        job.setJobTitle("Dr");
        job.setLocation("Location");
        job.setPostedBy(recruiter);
        job.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job.setSkill("Skill");

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setAppliedJobs(new ArrayList<>());
        jobSeeker.setAttachment(new Attachment());
        jobSeeker.setEmail("jane.doe@example.org");
        jobSeeker.setFirstName("Jane");
        jobSeeker.setId(123L);
        jobSeeker.setLastName("Doe");
        jobSeeker.setPassword("iloveyou");
        jobSeeker.setSkill("Skill");

        Attachment attachment = new Attachment();
        attachment.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment.setFileName("foo.txt");
        attachment.setFileType("File Type");
        attachment.setId("42");
        attachment.setJobSeeker(jobSeeker);

        JobSeeker jobSeeker1 = new JobSeeker();
        jobSeeker1.setAppliedJobs(new ArrayList<>());
        jobSeeker1.setAttachment(attachment);
        jobSeeker1.setEmail("jane.doe@example.org");
        jobSeeker1.setFirstName("Jane");
        jobSeeker1.setId(123L);
        jobSeeker1.setLastName("Doe");
        jobSeeker1.setPassword("iloveyou");
        jobSeeker1.setSkill("Skill");

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());

        JobApplication jobApplication = new JobApplication();
        jobApplication.setAppliedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        jobApplication.setId(123L);
        jobApplication.setJob(job);
        jobApplication.setJobSeeker(jobSeeker1);
        jobApplication.setRecruiter(recruiter1);
        jobApplication.setStatus("Status");
        Optional<JobApplication> ofResult = Optional.of(jobApplication);

        Recruiter recruiter2 = new Recruiter();
        recruiter2.setCompanyName("Company Name");
        recruiter2.setEmail("jane.doe@example.org");
        recruiter2.setFirstName("Jane");
        recruiter2.setId(123L);
        recruiter2.setJobApplications(new ArrayList<>());
        recruiter2.setLastName("Doe");
        recruiter2.setPassword("iloveyou");
        recruiter2.setPostedJobs(new ArrayList<>());

        Job job1 = new Job();
        job1.setCompanyName("Company Name");
        job1.setId(123L);
        job1.setJobApplications(new ArrayList<>());
        job1.setJobDescription("Job Description");
        job1.setJobTitle("Dr");
        job1.setLocation("Location");
        job1.setPostedBy(recruiter2);
        job1.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job1.setSkill("Skill");

        Attachment attachment1 = new Attachment();
        attachment1.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment1.setFileName("foo.txt");
        attachment1.setFileType("File Type");
        attachment1.setId("42");
        attachment1.setJobSeeker(new JobSeeker());

        JobSeeker jobSeeker2 = new JobSeeker();
        jobSeeker2.setAppliedJobs(new ArrayList<>());
        jobSeeker2.setAttachment(attachment1);
        jobSeeker2.setEmail("jane.doe@example.org");
        jobSeeker2.setFirstName("Jane");
        jobSeeker2.setId(123L);
        jobSeeker2.setLastName("Doe");
        jobSeeker2.setPassword("iloveyou");
        jobSeeker2.setSkill("Skill");

        Attachment attachment2 = new Attachment();
        attachment2.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment2.setFileName("foo.txt");
        attachment2.setFileType("File Type");
        attachment2.setId("42");
        attachment2.setJobSeeker(jobSeeker2);

        JobSeeker jobSeeker3 = new JobSeeker();
        jobSeeker3.setAppliedJobs(new ArrayList<>());
        jobSeeker3.setAttachment(attachment2);
        jobSeeker3.setEmail("jane.doe@example.org");
        jobSeeker3.setFirstName("Jane");
        jobSeeker3.setId(123L);
        jobSeeker3.setLastName("Doe");
        jobSeeker3.setPassword("iloveyou");
        jobSeeker3.setSkill("Skill");

        Recruiter recruiter3 = new Recruiter();
        recruiter3.setCompanyName("Company Name");
        recruiter3.setEmail("jane.doe@example.org");
        recruiter3.setFirstName("Jane");
        recruiter3.setId(123L);
        recruiter3.setJobApplications(new ArrayList<>());
        recruiter3.setLastName("Doe");
        recruiter3.setPassword("iloveyou");
        recruiter3.setPostedJobs(new ArrayList<>());

        JobApplication jobApplication1 = new JobApplication();
        jobApplication1.setAppliedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        jobApplication1.setId(123L);
        jobApplication1.setJob(job1);
        jobApplication1.setJobSeeker(jobSeeker3);
        jobApplication1.setRecruiter(recruiter3);
        jobApplication1.setStatus("Status");
        when(iJobApplicationDao.save((JobApplication) any())).thenReturn(jobApplication1);
        when(iJobApplicationDao.findById((Long) any())).thenReturn(ofResult);
        when(iJobApplicationDao.existsById((Long) any())).thenReturn(true);
        JobApplication actualUpdateJobApplicationResult = jobApplicationServiceImpl.updateJobApplication(123L,
                new JobApplicationDTO());
        assertSame(jobApplication, actualUpdateJobApplicationResult);
        assertNull(actualUpdateJobApplicationResult.getStatus());
        verify(iJobApplicationDao).existsById((Long) any());
        verify(iJobApplicationDao).save((JobApplication) any());
        verify(iJobApplicationDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#updateJobApplication(Long, JobApplicationDTO)}
     */
    @Test
    void testUpdateJobApplication2() throws UnsupportedEncodingException {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());

        Job job = new Job();
        job.setCompanyName("Company Name");
        job.setId(123L);
        job.setJobApplications(new ArrayList<>());
        job.setJobDescription("Job Description");
        job.setJobTitle("Dr");
        job.setLocation("Location");
        job.setPostedBy(recruiter);
        job.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job.setSkill("Skill");

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setAppliedJobs(new ArrayList<>());
        jobSeeker.setAttachment(new Attachment());
        jobSeeker.setEmail("jane.doe@example.org");
        jobSeeker.setFirstName("Jane");
        jobSeeker.setId(123L);
        jobSeeker.setLastName("Doe");
        jobSeeker.setPassword("iloveyou");
        jobSeeker.setSkill("Skill");

        Attachment attachment = new Attachment();
        attachment.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment.setFileName("foo.txt");
        attachment.setFileType("File Type");
        attachment.setId("42");
        attachment.setJobSeeker(jobSeeker);

        JobSeeker jobSeeker1 = new JobSeeker();
        jobSeeker1.setAppliedJobs(new ArrayList<>());
        jobSeeker1.setAttachment(attachment);
        jobSeeker1.setEmail("jane.doe@example.org");
        jobSeeker1.setFirstName("Jane");
        jobSeeker1.setId(123L);
        jobSeeker1.setLastName("Doe");
        jobSeeker1.setPassword("iloveyou");
        jobSeeker1.setSkill("Skill");

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());

        JobApplication jobApplication = new JobApplication();
        jobApplication.setAppliedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        jobApplication.setId(123L);
        jobApplication.setJob(job);
        jobApplication.setJobSeeker(jobSeeker1);
        jobApplication.setRecruiter(recruiter1);
        jobApplication.setStatus("Status");
        Optional<JobApplication> ofResult = Optional.of(jobApplication);
        when(iJobApplicationDao.save((JobApplication) any()))
                .thenThrow(new InvalidJobApplicationException("An error occurred"));
        when(iJobApplicationDao.findById((Long) any())).thenReturn(ofResult);
        when(iJobApplicationDao.existsById((Long) any())).thenReturn(true);
        assertThrows(InvalidJobApplicationException.class,
                () -> jobApplicationServiceImpl.updateJobApplication(123L, new JobApplicationDTO()));
        verify(iJobApplicationDao).existsById((Long) any());
        verify(iJobApplicationDao).save((JobApplication) any());
        verify(iJobApplicationDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#findJobSeekersApplications(Long)}
     */
    @Test
    void testFindJobSeekersApplications() {
        ArrayList<JobApplicationsListDTO> jobApplicationsListDTOList = new ArrayList<>();
        when(iJobApplicationDao.findJobSeekersApplications((Long) any())).thenReturn(jobApplicationsListDTOList);
        List<JobApplicationsListDTO> actualFindJobSeekersApplicationsResult = jobApplicationServiceImpl
                .findJobSeekersApplications(1L);
        assertSame(jobApplicationsListDTOList, actualFindJobSeekersApplicationsResult);
        assertTrue(actualFindJobSeekersApplicationsResult.isEmpty());
        verify(iJobApplicationDao).findJobSeekersApplications((Long) any());
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#findJobSeekersApplications(Long)}
     */
    @Test
    void testFindJobSeekersApplications2() {
        when(iJobApplicationDao.findJobSeekersApplications((Long) any()))
                .thenThrow(new InvalidJobApplicationException("An error occurred"));
        assertThrows(InvalidJobApplicationException.class,
                () -> jobApplicationServiceImpl.findJobSeekersApplications(1L));
        verify(iJobApplicationDao).findJobSeekersApplications((Long) any());
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#viewAllApplicationsByRecruiterId(Long)}
     */
    @Test
    void testViewAllApplicationsByRecruiterId() {
        ArrayList<JobSeekerDetailsDTO> jobSeekerDetailsDTOList = new ArrayList<>();
        when(iJobApplicationDao.findJobSeekersById((Long) any())).thenReturn(jobSeekerDetailsDTOList);
        List<JobSeekerDetailsDTO> actualViewAllApplicationsByRecruiterIdResult = jobApplicationServiceImpl
                .viewAllApplicationsByRecruiterId(123L);
        assertSame(jobSeekerDetailsDTOList, actualViewAllApplicationsByRecruiterIdResult);
        assertTrue(actualViewAllApplicationsByRecruiterIdResult.isEmpty());
        verify(iJobApplicationDao).findJobSeekersById((Long) any());
    }

    /**
     * Method under test: {@link JobApplicationServiceImpl#viewAllApplicationsByRecruiterId(Long)}
     */
    @Test
    void testViewAllApplicationsByRecruiterId2() {
        when(iJobApplicationDao.findJobSeekersById((Long) any()))
                .thenThrow(new InvalidJobApplicationException("An error occurred"));
        assertThrows(InvalidJobApplicationException.class,
                () -> jobApplicationServiceImpl.viewAllApplicationsByRecruiterId(123L));
        verify(iJobApplicationDao).findJobSeekersById((Long) any());
    }
}

