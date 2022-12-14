package com.example.jobboardapp.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.jobboardapp.dto.RecruiterDTO;
import com.example.jobboardapp.entities.Recruiter;
import com.example.jobboardapp.service.IRecruiterService;
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

@ContextConfiguration(classes = {RecruiterController.class})
@ExtendWith(SpringExtension.class)
class RecruiterControllerTest {
    @MockBean
    private IRecruiterService iRecruiterService;

    @Autowired
    private RecruiterController recruiterController;

    /**
     * Method under test: {@link RecruiterController#createRecruiter(Recruiter, BindingResult)}
     */
    @Test
    void testCreateRecruiter() throws Exception {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());
        when(iRecruiterService.save((Recruiter) any())).thenReturn(recruiter);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(recruiter1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/recruiter/createRecruiter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(recruiterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Added successfully"));
    }

    /**
     * Method under test: {@link RecruiterController#getRecruiterById(Long)}
     */
    @Test
    void testGetRecruiterById() throws Exception {
        when(iRecruiterService.findById((Long) any())).thenReturn(new RecruiterDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/recruiter/getRecruiterById/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(recruiterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"email\":null,\"firstName\":null,\"lastName\":null}"));
    }

    /**
     * Method under test: {@link RecruiterController#updateRecruiter(Long, Recruiter)}
     */
    @Test
    void testUpdateRecruiter() throws Exception {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());
        when(iRecruiterService.update((Long) any(), (Recruiter) any())).thenReturn(recruiter);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(recruiter1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/recruiter/updateRecruiter/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(recruiterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Updated Recruiter Successfully"));
    }
}

