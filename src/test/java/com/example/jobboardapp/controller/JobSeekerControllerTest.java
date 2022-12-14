package com.example.jobboardapp.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.jobboardapp.dto.JobSeekerDTO;
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

@ContextConfiguration(classes = {JobSeekerController.class})
@ExtendWith(SpringExtension.class)
class JobSeekerControllerTest {
    @MockBean
    private IJobSeekerService iJobSeekerService;

    @Autowired
    private JobSeekerController jobSeekerController;

    /**
     * Method under test: {@link JobSeekerController#createJobSeeker(JobSeeker)}
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
        when(iJobSeekerService.createJobSeeker((JobSeeker) any())).thenReturn(jobSeeker2);

        JobSeeker jobSeeker3 = new JobSeeker();
        jobSeeker3.setAppliedJobs(new ArrayList<>());
        jobSeeker3.setAttachment(new Attachment());
        jobSeeker3.setEmail("jane.doe@example.org");
        jobSeeker3.setFirstName("Jane");
        jobSeeker3.setId(123L);
        jobSeeker3.setLastName("Doe");
        jobSeeker3.setPassword("iloveyou");
        jobSeeker3.setSkill("Skill");

        Attachment attachment2 = new Attachment();
        attachment2.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment2.setFileName("foo.txt");
        attachment2.setFileType("File Type");
        attachment2.setId("42");
        attachment2.setJobSeeker(jobSeeker3);

        JobSeeker jobSeeker4 = new JobSeeker();
        jobSeeker4.setAppliedJobs(new ArrayList<>());
        jobSeeker4.setAttachment(attachment2);
        jobSeeker4.setEmail("jane.doe@example.org");
        jobSeeker4.setFirstName("Jane");
        jobSeeker4.setId(123L);
        jobSeeker4.setLastName("Doe");
        jobSeeker4.setPassword("iloveyou");
        jobSeeker4.setSkill("Skill");

        Attachment attachment3 = new Attachment();
        attachment3.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment3.setFileName("foo.txt");
        attachment3.setFileType("File Type");
        attachment3.setId("42");
        attachment3.setJobSeeker(jobSeeker4);

        JobSeeker jobSeeker5 = new JobSeeker();
        jobSeeker5.setAppliedJobs(new ArrayList<>());
        jobSeeker5.setAttachment(attachment3);
        jobSeeker5.setEmail("jane.doe@example.org");
        jobSeeker5.setFirstName("Jane");
        jobSeeker5.setId(123L);
        jobSeeker5.setLastName("Doe");
        jobSeeker5.setPassword("iloveyou");
        jobSeeker5.setSkill("Skill");
        String content = (new ObjectMapper()).writeValueAsString(jobSeeker5);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jobSeeker/createJobSeeker")
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobSeeker/getJobSeekerById/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(jobSeekerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"firstName\":null,\"lastName\":null,\"email\":null}"));
    }

    /**
     * Method under test: {@link JobSeekerController#updateJobSeeker(Long, JobSeeker)}
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
        when(iJobSeekerService.updateJobSeeker((Long) any(), (JobSeeker) any())).thenReturn(jobSeeker2);

        JobSeeker jobSeeker3 = new JobSeeker();
        jobSeeker3.setAppliedJobs(new ArrayList<>());
        jobSeeker3.setAttachment(new Attachment());
        jobSeeker3.setEmail("jane.doe@example.org");
        jobSeeker3.setFirstName("Jane");
        jobSeeker3.setId(123L);
        jobSeeker3.setLastName("Doe");
        jobSeeker3.setPassword("iloveyou");
        jobSeeker3.setSkill("Skill");

        Attachment attachment2 = new Attachment();
        attachment2.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment2.setFileName("foo.txt");
        attachment2.setFileType("File Type");
        attachment2.setId("42");
        attachment2.setJobSeeker(jobSeeker3);

        JobSeeker jobSeeker4 = new JobSeeker();
        jobSeeker4.setAppliedJobs(new ArrayList<>());
        jobSeeker4.setAttachment(attachment2);
        jobSeeker4.setEmail("jane.doe@example.org");
        jobSeeker4.setFirstName("Jane");
        jobSeeker4.setId(123L);
        jobSeeker4.setLastName("Doe");
        jobSeeker4.setPassword("iloveyou");
        jobSeeker4.setSkill("Skill");

        Attachment attachment3 = new Attachment();
        attachment3.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment3.setFileName("foo.txt");
        attachment3.setFileType("File Type");
        attachment3.setId("42");
        attachment3.setJobSeeker(jobSeeker4);

        JobSeeker jobSeeker5 = new JobSeeker();
        jobSeeker5.setAppliedJobs(new ArrayList<>());
        jobSeeker5.setAttachment(attachment3);
        jobSeeker5.setEmail("jane.doe@example.org");
        jobSeeker5.setFirstName("Jane");
        jobSeeker5.setId(123L);
        jobSeeker5.setLastName("Doe");
        jobSeeker5.setPassword("iloveyou");
        jobSeeker5.setSkill("Skill");
        String content = (new ObjectMapper()).writeValueAsString(jobSeeker5);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/jobSeeker/updateJobSeeker/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(jobSeekerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Updated Freelancer Successfully"));
    }

    /**
     * Method under test: {@link JobSeekerController#searchBySkill(String)}
     */
    @Test
    void testSearchBySkill() throws Exception {
        when(iJobSeekerService.searchBySkill((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobSeeker/searchBySkill/{skill}",
                "Skill");
        MockMvcBuilders.standaloneSetup(jobSeekerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobSeekerController#listAllJobSeeker()}
     */
    @Test
    void testListAllJobSeeker() throws Exception {
        when(iJobSeekerService.findAllJobSeeker()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/jobSeeker/listAllJobSeeker");
        MockMvcBuilders.standaloneSetup(jobSeekerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

