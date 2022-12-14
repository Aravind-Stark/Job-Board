package com.example.jobboardapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        when(iAttachmentDao.save((Attachment) any())).thenReturn(attachment2);
        assertSame(attachment2, attachementServiceImpl
                .uploadResume(new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))), 123L));
        verify(iAttachmentDao).save((Attachment) any());
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

