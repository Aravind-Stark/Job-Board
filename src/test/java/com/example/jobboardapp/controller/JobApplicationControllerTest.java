package com.example.jobboardapp.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.jobboardapp.dto.JobApplicationDTO;
import com.example.jobboardapp.dto.JobApplicationStatusDTO;
import com.example.jobboardapp.entities.Attachment;
import com.example.jobboardapp.entities.Job;
import com.example.jobboardapp.entities.JobApplication;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.entities.Recruiter;
import com.example.jobboardapp.service.IJobApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {JobApplicationController.class})
@ExtendWith(SpringExtension.class)
class JobApplicationControllerTest {
    @MockBean
    private IJobApplicationService iJobApplicationService;

    @Autowired
    private JobApplicationController jobApplicationController;

    /**
     * Method under test: {@link JobApplicationController#applyToJob(JobApplicationDTO, BindingResult)}
     */
    @Test
    void testApplyToJob() throws Exception {
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
        when(iJobApplicationService.applyToJob((JobApplicationDTO) any())).thenReturn(jobApplication);

        JobApplicationDTO jobApplicationDTO = new JobApplicationDTO();
        jobApplicationDTO.setJobId(123L);
        jobApplicationDTO.setJobseekerId(123L);
        jobApplicationDTO.setRecruiterId(123L);
        jobApplicationDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(jobApplicationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/jobApplication/apply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jobApplicationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job Applied Successfully"));
    }

    /**
     * Method under test: {@link JobApplicationController#findJobSeekersApplications(Long)}
     */
    @Test
    void testFindJobSeekersApplications() throws Exception {
        when(iJobApplicationService.findJobSeekersApplications((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/jobApplication/findJobSeekersApplications/{jobSeekerID}", 1L);
        MockMvcBuilders.standaloneSetup(jobApplicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobApplicationController#viewAllApplicationsByRecruiterId(Long)}
     */
    @Test
    void testViewAllApplicationsByRecruiterId() throws Exception {
        when(iJobApplicationService.viewAllApplicationsByRecruiterId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/jobApplication/viewAllApplicationsByRecruiterId/{recruiterId}", 123L);
        MockMvcBuilders.standaloneSetup(jobApplicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobApplicationController#updateJobApplicationStatus(JobApplicationStatusDTO, BindingResult, Long)}
     */
    @Test
    void testUpdateJobApplicationStatus() throws Exception {
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
        when(iJobApplicationService.updateJobApplication((Long) any(), (JobApplicationStatusDTO) any()))
                .thenReturn(jobApplication);

        JobApplicationStatusDTO jobApplicationStatusDTO = new JobApplicationStatusDTO();
        jobApplicationStatusDTO.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(jobApplicationStatusDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/jobApplication/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(jobApplicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("job application updated successfully"));
    }
}

