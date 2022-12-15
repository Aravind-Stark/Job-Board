package com.example.jobboardapp.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.jobboardapp.dto.JobSeekerDTO;
import com.example.jobboardapp.dto.JobSeekerRegisterDTO;
import com.example.jobboardapp.dto.UserLoginDTO;
import com.example.jobboardapp.entities.Attachment;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.service.IJobSeekerService;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@ContextConfiguration(classes = {JobSeekerController.class})
@ExtendWith(SpringExtension.class)
class JobSeekerControllerTest {
    @MockBean
    private IJobSeekerService iJobSeekerService;

    @Autowired
    private JobSeekerController jobSeekerController;

    /**
     * Method under test: {@link JobSeekerController#createJobSeeker(JobSeekerRegisterDTO, BindingResult)}
     */
    @Test
    void testCreateJobSeeker() throws Exception {
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

        Attachment attachment1 = new Attachment();
        attachment1.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment1.setFileName("foo.txt");
        attachment1.setFileType("File Type");
        attachment1.setId("42");
        attachment1.setJobSeeker(jobSeeker1);

        JobSeeker jobSeeker2 = new JobSeeker();
        jobSeeker2.setAppliedJobs(new ArrayList<>());
        jobSeeker2.setAttachment(attachment1);
        jobSeeker2.setEmail("jane.doe@example.org");
        jobSeeker2.setFirstName("Jane");
        jobSeeker2.setId(123L);
        jobSeeker2.setLastName("Doe");
        jobSeeker2.setPassword("iloveyou");
        jobSeeker2.setSkill("Skill");
        when(iJobSeekerService.createJobSeeker((JobSeekerRegisterDTO) any())).thenReturn(jobSeeker2);

        JobSeekerRegisterDTO jobSeekerRegisterDTO = new JobSeekerRegisterDTO();
        jobSeekerRegisterDTO.setEmail("jane.doe@example.org");
        jobSeekerRegisterDTO.setFirstName("Jane");
        jobSeekerRegisterDTO.setLastName("Doe");
        jobSeekerRegisterDTO.setPassword("iloveyou");
        jobSeekerRegisterDTO.setSkill("Skill");
        String content = (new ObjectMapper()).writeValueAsString(jobSeekerRegisterDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/jobSeeker/createJobSeeker")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jobSeekerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Added successfully"));
    }

    /**
     * Method under test: {@link JobSeekerController#getJobSeekerById(Long)}
     */
    @Test
    void testGetJobSeekerById() throws Exception {
        when(iJobSeekerService.getJobSeekerById((Long) any())).thenReturn(new JobSeekerDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/jobSeeker/getJobSeekerById/{id}", 123L);
        MockMvcBuilders.standaloneSetup(jobSeekerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"firstName\":null,\"lastName\":null,\"email\":null}"));
    }

    /**
     * Method under test: {@link JobSeekerController#updateJobSeeker(JobSeekerRegisterDTO, BindingResult, Long)}
     */
    @Test
    void testUpdateJobSeeker() throws Exception {
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

        Attachment attachment1 = new Attachment();
        attachment1.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment1.setFileName("foo.txt");
        attachment1.setFileType("File Type");
        attachment1.setId("42");
        attachment1.setJobSeeker(jobSeeker1);

        JobSeeker jobSeeker2 = new JobSeeker();
        jobSeeker2.setAppliedJobs(new ArrayList<>());
        jobSeeker2.setAttachment(attachment1);
        jobSeeker2.setEmail("jane.doe@example.org");
        jobSeeker2.setFirstName("Jane");
        jobSeeker2.setId(123L);
        jobSeeker2.setLastName("Doe");
        jobSeeker2.setPassword("iloveyou");
        jobSeeker2.setSkill("Skill");
        when(iJobSeekerService.updateJobSeeker((Long) any(), (JobSeekerRegisterDTO) any())).thenReturn(jobSeeker2);

        JobSeekerRegisterDTO jobSeekerRegisterDTO = new JobSeekerRegisterDTO();
        jobSeekerRegisterDTO.setEmail("jane.doe@example.org");
        jobSeekerRegisterDTO.setFirstName("Jane");
        jobSeekerRegisterDTO.setLastName("Doe");
        jobSeekerRegisterDTO.setPassword("iloveyou");
        jobSeekerRegisterDTO.setSkill("Skill");
        String content = (new ObjectMapper()).writeValueAsString(jobSeekerRegisterDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/jobSeeker/updateJobSeeker/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(jobSeekerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Updated JobSeeker Successfully"));
    }

    /**
     * Method under test: {@link JobSeekerController#searchBySkill(String)}
     */
    @Test
    void testSearchBySkill() throws Exception {
        when(iJobSeekerService.searchBySkill((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/jobSeeker/searchBySkill/{skill}", "Skill");
        MockMvcBuilders.standaloneSetup(jobSeekerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobSeekerController#jobSeekerLogin(UserLoginDTO)}
     */
    @Test
    void testJobSeekerLogin() throws Exception {
        when(iJobSeekerService.jobSeekerLogin((UserLoginDTO) any())).thenReturn("Job Seeker Login");

        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("jane.doe@example.org");
        userLoginDTO.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(userLoginDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/jobSeeker/jobSeekerLogin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(jobSeekerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Job Seeker Login"));
    }

    /**
     * Method under test: {@link JobSeekerController#listAllJobSeeker()}
     */
    @Test
    void testListAllJobSeeker() throws Exception {
        when(iJobSeekerService.findAllJobSeeker()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/jobSeeker/listAllJobSeeker");
        MockMvcBuilders.standaloneSetup(jobSeekerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

