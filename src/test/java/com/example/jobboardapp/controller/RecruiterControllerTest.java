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

}

