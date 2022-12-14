package com.example.jobboardapp.controller;

import com.example.jobboardapp.service.IAttachementService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {AttachmentController.class})
@ExtendWith(SpringExtension.class)
class AttachmentControllerTest {
    @Autowired
    private AttachmentController attachmentController;

    @MockBean
    private IAttachementService iAttachementService;

    /**
     * Method under test: {@link AttachmentController#downloadFile(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDownloadFile() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.jobboardapp.controller.AttachmentController.downloadFile(AttachmentController.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        (new AttachmentController()).downloadFile(1L);
    }

    /**
     * Method under test: {@link AttachmentController#uploadResume(MultipartFile, Long)}
     */
    @Test
    void testUploadResume() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/uploadResume/{id}", "", "Uri Vars");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("multipartFile", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(attachmentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

