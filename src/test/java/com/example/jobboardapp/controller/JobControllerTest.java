package com.example.jobboardapp.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.jobboardapp.dto.JobDTO;
import com.example.jobboardapp.entities.Job;
import com.example.jobboardapp.entities.Recruiter;
import com.example.jobboardapp.service.IJobService;
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

@ContextConfiguration(classes = {JobController.class})
@ExtendWith(SpringExtension.class)
class JobControllerTest {
    @MockBean
    private IJobService iJobService;

    @Autowired
    private JobController jobController;

    /**
     * Method under test: {@link JobController#postJob(JobDTO, BindingResult)}
     */
    @Test
    void testPostJob2() throws Exception {
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
        when(iJobService.postJob((JobDTO) any())).thenReturn(job);

        JobDTO jobDTO = new JobDTO();
        jobDTO.setCompanyName("Company Name");
        jobDTO.setJobDescription("Job Description");
        jobDTO.setJobSeekerId(123L);
        jobDTO.setJobTitle("Dr");
        jobDTO.setLocation("Location");
        jobDTO.setPostedDate(null);
        jobDTO.setRecruiterId(123L);
        jobDTO.setSkill("Skill");
        String content = (new ObjectMapper()).writeValueAsString(jobDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/job/postJob")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job Posted Successfully"));
    }

    /**
     * Method under test: {@link JobController#updateJob(JobDTO, BindingResult, Long)}
     */
    @Test
    void testUpdateJob2() throws Exception {
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
        when(iJobService.updateJob((JobDTO) any(), (Long) any())).thenReturn(job);

        JobDTO jobDTO = new JobDTO();
        jobDTO.setCompanyName("Company Name");
        jobDTO.setJobDescription("Job Description");
        jobDTO.setJobSeekerId(123L);
        jobDTO.setJobTitle("Dr");
        jobDTO.setLocation("Location");
        jobDTO.setPostedDate(null);
        jobDTO.setRecruiterId(123L);
        jobDTO.setSkill("Skill");
        String content = (new ObjectMapper()).writeValueAsString(jobDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/job/updateJob/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job updated Successfully"));
    }

    /**
     * Method under test: {@link JobController#deleteJob(Long)}
     */
    @Test
    void testDeleteJob() throws Exception {
        doNothing().when(iJobService).deleteJob((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/job/deleteJob/{id}", 123L);
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("deleted successfully"));
    }

    /**
     * Method under test: {@link JobController#findByRecruiterId(Long)}
     */
    @Test
    void testFindByRecruiterId() throws Exception {
        when(iJobService.findByRecruiterId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/job/findByRecruiterId/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobController#findJobsBySkill(String)}
     */
    @Test
    void testFindJobsBySkill() throws Exception {
        when(iJobService.findJobsBySkill((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/job/findJobsBySkill/{skill}",
                "Skill");
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobController#findJobsByTitle(String)}
     */
    @Test
    void testFindJobsByTitle() throws Exception {
        when(iJobService.findJobsByTitle((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/job/findJobsByTitle/{title}",
                "Dr");
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobController#findJobsByLocation(String)}
     */
    @Test
    void testFindJobsByLocation() throws Exception {
        when(iJobService.findJobsByLocation((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/job/findJobsByLocation/{location}", "Location");
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobController#findAllJobs()}
     */
    @Test
    void testFindAllJobs() throws Exception {
        when(iJobService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/job/findAllJobs");
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobController#postJob(JobDTO, BindingResult)}
     */
    @Test
    void testPostJob() throws Exception {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setCompanyName("Company Name");
        jobDTO.setJobDescription("Job Description");
        jobDTO.setJobSeekerId(123L);
        jobDTO.setJobTitle("Dr");
        jobDTO.setLocation("Location");
        jobDTO.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        jobDTO.setRecruiterId(123L);
        jobDTO.setSkill("Skill");
        String content = (new ObjectMapper()).writeValueAsString(jobDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/job/postJob")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link JobController#sayHelloBro()}
     */
    @Test
    void testSayHelloBro() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/job/hello");
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Hello bro"));
    }

    /**
     * Method under test: {@link JobController#sayHelloBro()}
     */
    @Test
    void testSayHelloBro2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/job/hello");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Hello bro"));
    }

    /**
     * Method under test: {@link JobController#updateJob(JobDTO, BindingResult, Long)}
     */
    @Test
    void testUpdateJob() throws Exception {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setCompanyName("Company Name");
        jobDTO.setJobDescription("Job Description");
        jobDTO.setJobSeekerId(123L);
        jobDTO.setJobTitle("Dr");
        jobDTO.setLocation("Location");
        jobDTO.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        jobDTO.setRecruiterId(123L);
        jobDTO.setSkill("Skill");
        String content = (new ObjectMapper()).writeValueAsString(jobDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/job/updateJob/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

