package com.example.jobboardapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.jobboardapp.dao.IAttachmentDao;
import com.example.jobboardapp.dao.IJobSeekerDao;
import com.example.jobboardapp.entities.Attachment;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.exceptions.InvalidJobSeekerException;

import java.io.ByteArrayInputStream;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {AttachementServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AttachementServiceImplTest {
    @Autowired
    private AttachementServiceImpl attachementServiceImpl;

    @MockBean
    private IAttachmentDao iAttachmentDao;

    @MockBean
    private IJobSeekerDao iJobSeekerDao;

    /**
     * Method under test: {@link AttachementServiceImpl#uploadResume(MultipartFile, Long)}
     */

    @Test
    void testUploadResume() throws Exception {

        // Set up mock multipartFile object
        MultipartFile mockMultipartFile = mock(MultipartFile.class);
        when(mockMultipartFile.getOriginalFilename()).thenReturn("resume.pdf");
        when(mockMultipartFile.getContentType()).thenReturn("application/pdf");
        when(mockMultipartFile.getBytes()).thenReturn("content".getBytes());

        // Set up expected return value
        Attachment expectedAttachment = new Attachment("resume.pdf", "application/pdf", "content".getBytes());
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setId(1L);
        expectedAttachment.setJobSeeker(jobSeeker);

        // Set up mock attachmentDao to return expectedAttachment when save method is called
        when(iAttachmentDao.save(any(Attachment.class))).thenReturn(expectedAttachment);

        // Set up mock jobSeekerDao to return true when existsById method is called
        when(iJobSeekerDao.existsById(anyLong())).thenReturn(true);
        // Set up mock attachmentDao to return null when findByJobSeekerId method is called
        when(iAttachmentDao.findByJobSeekerId(anyLong())).thenReturn(null);

        // Call uploadResume and store result
        Attachment result = attachementServiceImpl.uploadResume(mockMultipartFile, 1L);

        // Verify that result is as expected
        assertEquals(expectedAttachment, result);
    }

    /**
     * Method under test: {@link AttachementServiceImpl#getAttachment(Long)}
     */
    @Test
    void testGetAttachment() throws UnsupportedEncodingException {
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

        Attachment attachment2 = new Attachment();
        attachment2.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment2.setFileName("foo.txt");
        attachment2.setFileType("File Type");
        attachment2.setId("42");
        attachment2.setJobSeeker(jobSeeker1);
        when(iAttachmentDao.findByJobSeekerId((Long) any())).thenReturn(attachment2);
        when(iJobSeekerDao.existsById((Long) any())).thenReturn(true);
        assertSame(attachment2, attachementServiceImpl.getAttachment(1L));
        verify(iAttachmentDao).findByJobSeekerId((Long) any());
        verify(iJobSeekerDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link AttachementServiceImpl#getAttachment(Long)}
     */
    @Test
    void testGetAttachment2() {
        when(iAttachmentDao.findByJobSeekerId((Long) any())).thenThrow(new InvalidJobSeekerException("Msg"));
        when(iJobSeekerDao.existsById((Long) any())).thenReturn(true);
        assertThrows(InvalidJobSeekerException.class, () -> attachementServiceImpl.getAttachment(1L));
        verify(iAttachmentDao).findByJobSeekerId((Long) any());
        verify(iJobSeekerDao).existsById((Long) any());
    }
}

