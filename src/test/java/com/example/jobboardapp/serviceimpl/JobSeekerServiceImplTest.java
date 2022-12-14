package com.example.jobboardapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jobboardapp.dao.IJobSeekerDao;
import com.example.jobboardapp.dto.JobSeekerDTO;
import com.example.jobboardapp.entities.Attachment;
import com.example.jobboardapp.entities.JobApplication;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.exceptions.InvalidJobSeekerException;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JobSeekerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class JobSeekerServiceImplTest {
    @MockBean
    private IJobSeekerDao iJobSeekerDao;

    @Autowired
    private JobSeekerServiceImpl jobSeekerServiceImpl;

    /**
     * Method under test: {@link JobSeekerServiceImpl#getJobSeekerById(Long)}
     */
    @Test
    void testGetJobSeekerById() throws UnsupportedEncodingException {
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
        Optional<JobSeeker> ofResult = Optional.of(jobSeeker1);
        when(iJobSeekerDao.findById((Long) any())).thenReturn(ofResult);
        when(iJobSeekerDao.existsById((Long) any())).thenReturn(true);
        JobSeekerDTO actualJobSeekerById = jobSeekerServiceImpl.getJobSeekerById(123L);
        assertEquals("jane.doe@example.org", actualJobSeekerById.getEmail());
        assertEquals("Doe", actualJobSeekerById.getLastName());
        assertEquals(123L, actualJobSeekerById.getId().longValue());
        assertEquals("Jane", actualJobSeekerById.getFirstName());
        verify(iJobSeekerDao).existsById((Long) any());
        verify(iJobSeekerDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#getJobSeekerById(Long)}
     */
    @Test
    void testGetJobSeekerById2() {
        when(iJobSeekerDao.findById((Long) any())).thenThrow(new InvalidJobSeekerException("Msg"));
        when(iJobSeekerDao.existsById((Long) any())).thenReturn(true);
        assertThrows(InvalidJobSeekerException.class, () -> jobSeekerServiceImpl.getJobSeekerById(123L));
        verify(iJobSeekerDao).existsById((Long) any());
        verify(iJobSeekerDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#getJobSeekerById(Long)}
     */
    @Test
    void testGetJobSeekerById3() throws UnsupportedEncodingException {
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
        JobSeeker jobSeeker1 = mock(JobSeeker.class);
        when(jobSeeker1.getId()).thenReturn(123L);
        when(jobSeeker1.getEmail()).thenReturn("jane.doe@example.org");
        when(jobSeeker1.getFirstName()).thenReturn("Jane");
        when(jobSeeker1.getLastName()).thenReturn("Doe");
        doNothing().when(jobSeeker1).setAppliedJobs((List<JobApplication>) any());
        doNothing().when(jobSeeker1).setAttachment((Attachment) any());
        doNothing().when(jobSeeker1).setEmail((String) any());
        doNothing().when(jobSeeker1).setFirstName((String) any());
        doNothing().when(jobSeeker1).setId((Long) any());
        doNothing().when(jobSeeker1).setLastName((String) any());
        doNothing().when(jobSeeker1).setPassword((String) any());
        doNothing().when(jobSeeker1).setSkill((String) any());
        jobSeeker1.setAppliedJobs(new ArrayList<>());
        jobSeeker1.setAttachment(attachment1);
        jobSeeker1.setEmail("jane.doe@example.org");
        jobSeeker1.setFirstName("Jane");
        jobSeeker1.setId(123L);
        jobSeeker1.setLastName("Doe");
        jobSeeker1.setPassword("iloveyou");
        jobSeeker1.setSkill("Skill");
        Optional<JobSeeker> ofResult = Optional.of(jobSeeker1);
        when(iJobSeekerDao.findById((Long) any())).thenReturn(ofResult);
        when(iJobSeekerDao.existsById((Long) any())).thenReturn(true);
        JobSeekerDTO actualJobSeekerById = jobSeekerServiceImpl.getJobSeekerById(123L);
        assertEquals("jane.doe@example.org", actualJobSeekerById.getEmail());
        assertEquals("Doe", actualJobSeekerById.getLastName());
        assertEquals(123L, actualJobSeekerById.getId().longValue());
        assertEquals("Jane", actualJobSeekerById.getFirstName());
        verify(iJobSeekerDao).existsById((Long) any());
        verify(iJobSeekerDao).findById((Long) any());
        verify(jobSeeker1).getId();
        verify(jobSeeker1).getEmail();
        verify(jobSeeker1).getFirstName();
        verify(jobSeeker1).getLastName();
        verify(jobSeeker1).setAppliedJobs((List<JobApplication>) any());
        verify(jobSeeker1).setAttachment((Attachment) any());
        verify(jobSeeker1).setEmail((String) any());
        verify(jobSeeker1).setFirstName((String) any());
        verify(jobSeeker1).setId((Long) any());
        verify(jobSeeker1).setLastName((String) any());
        verify(jobSeeker1).setPassword((String) any());
        verify(jobSeeker1).setSkill((String) any());
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#getCurrentId()}
     */
    @Test
    void testGetCurrentId() {
        when(iJobSeekerDao.getCurrentSeriesId()).thenReturn(123L);
        assertEquals(123L, jobSeekerServiceImpl.getCurrentId().longValue());
        verify(iJobSeekerDao).getCurrentSeriesId();
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#getCurrentId()}
     */
    @Test
    void testGetCurrentId2() {
        when(iJobSeekerDao.getCurrentSeriesId()).thenThrow(new InvalidJobSeekerException("Msg"));
        assertThrows(InvalidJobSeekerException.class, () -> jobSeekerServiceImpl.getCurrentId());
        verify(iJobSeekerDao).getCurrentSeriesId();
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#createJobSeeker(JobSeeker)}
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
        when(iJobSeekerDao.save((JobSeeker) any())).thenReturn(jobSeeker2);

        Attachment attachment2 = new Attachment();
        attachment2.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment2.setFileName("foo.txt");
        attachment2.setFileType("File Type");
        attachment2.setId("42");
        attachment2.setJobSeeker(new JobSeeker());

        JobSeeker jobSeeker3 = new JobSeeker();
        jobSeeker3.setAppliedJobs(new ArrayList<>());
        jobSeeker3.setAttachment(attachment2);
        jobSeeker3.setEmail("jane.doe@example.org");
        jobSeeker3.setFirstName("Jane");
        jobSeeker3.setId(123L);
        jobSeeker3.setLastName("Doe");
        jobSeeker3.setPassword("iloveyou");
        jobSeeker3.setSkill("Skill");

        Attachment attachment3 = new Attachment();
        attachment3.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment3.setFileName("foo.txt");
        attachment3.setFileType("File Type");
        attachment3.setId("42");
        attachment3.setJobSeeker(jobSeeker3);

        JobSeeker jobSeeker4 = new JobSeeker();
        jobSeeker4.setAppliedJobs(new ArrayList<>());
        jobSeeker4.setAttachment(attachment3);
        jobSeeker4.setEmail("jane.doe@example.org");
        jobSeeker4.setFirstName("Jane");
        jobSeeker4.setId(123L);
        jobSeeker4.setLastName("Doe");
        jobSeeker4.setPassword("iloveyou");
        jobSeeker4.setSkill("Skill");
        assertSame(jobSeeker2, jobSeekerServiceImpl.createJobSeeker(jobSeeker4));
        verify(iJobSeekerDao).save((JobSeeker) any());
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#updateJobSeeker(Long, JobSeeker)}
     */
    @Test
    void testUpdateJobSeeker() throws UnsupportedEncodingException {
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
        when(iJobSeekerDao.save((JobSeeker) any())).thenReturn(jobSeeker2);
        when(iJobSeekerDao.existsById((Long) any())).thenReturn(true);

        Attachment attachment2 = new Attachment();
        attachment2.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment2.setFileName("foo.txt");
        attachment2.setFileType("File Type");
        attachment2.setId("42");
        attachment2.setJobSeeker(new JobSeeker());

        JobSeeker jobSeeker3 = new JobSeeker();
        jobSeeker3.setAppliedJobs(new ArrayList<>());
        jobSeeker3.setAttachment(attachment2);
        jobSeeker3.setEmail("jane.doe@example.org");
        jobSeeker3.setFirstName("Jane");
        jobSeeker3.setId(123L);
        jobSeeker3.setLastName("Doe");
        jobSeeker3.setPassword("iloveyou");
        jobSeeker3.setSkill("Skill");

        Attachment attachment3 = new Attachment();
        attachment3.setData("AAAAAAAA".getBytes("UTF-8"));
        attachment3.setFileName("foo.txt");
        attachment3.setFileType("File Type");
        attachment3.setId("42");
        attachment3.setJobSeeker(jobSeeker3);

        JobSeeker jobSeeker4 = new JobSeeker();
        jobSeeker4.setAppliedJobs(new ArrayList<>());
        jobSeeker4.setAttachment(attachment3);
        jobSeeker4.setEmail("jane.doe@example.org");
        jobSeeker4.setFirstName("Jane");
        jobSeeker4.setId(123L);
        jobSeeker4.setLastName("Doe");
        jobSeeker4.setPassword("iloveyou");
        jobSeeker4.setSkill("Skill");
        assertSame(jobSeeker2, jobSeekerServiceImpl.updateJobSeeker(123L, jobSeeker4));
        verify(iJobSeekerDao).existsById((Long) any());
        verify(iJobSeekerDao).save((JobSeeker) any());
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#updateJobSeeker(Long, JobSeeker)}
     */
    @Test
    void testUpdateJobSeeker2() throws UnsupportedEncodingException {
        when(iJobSeekerDao.save((JobSeeker) any())).thenThrow(new InvalidJobSeekerException("Msg"));
        when(iJobSeekerDao.existsById((Long) any())).thenReturn(true);

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
        assertThrows(InvalidJobSeekerException.class, () -> jobSeekerServiceImpl.updateJobSeeker(123L, jobSeeker1));
        verify(iJobSeekerDao).existsById((Long) any());
        verify(iJobSeekerDao).save((JobSeeker) any());
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#findAllJobSeeker()}
     */
    @Test
    void testFindAllJobSeeker() {
        ArrayList<JobSeekerDTO> jobSeekerDTOList = new ArrayList<>();
        when(iJobSeekerDao.findAllJobSeeker()).thenReturn(jobSeekerDTOList);
        List<JobSeekerDTO> actualFindAllJobSeekerResult = jobSeekerServiceImpl.findAllJobSeeker();
        assertSame(jobSeekerDTOList, actualFindAllJobSeekerResult);
        assertTrue(actualFindAllJobSeekerResult.isEmpty());
        verify(iJobSeekerDao).findAllJobSeeker();
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#findAllJobSeeker()}
     */
    @Test
    void testFindAllJobSeeker2() {
        when(iJobSeekerDao.findAllJobSeeker()).thenThrow(new InvalidJobSeekerException("Msg"));
        assertThrows(InvalidJobSeekerException.class, () -> jobSeekerServiceImpl.findAllJobSeeker());
        verify(iJobSeekerDao).findAllJobSeeker();
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#searchBySkill(String)}
     */
    @Test
    void testSearchBySkill() {
        when(iJobSeekerDao.findBySkillContainingIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(jobSeekerServiceImpl.searchBySkill("Skill").isEmpty());
        verify(iJobSeekerDao).findBySkillContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#searchBySkill(String)}
     */
    @Test
    void testSearchBySkill2() throws UnsupportedEncodingException {
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

        ArrayList<JobSeeker> jobSeekerList = new ArrayList<>();
        jobSeekerList.add(jobSeeker1);
        when(iJobSeekerDao.findBySkillContainingIgnoreCase((String) any())).thenReturn(jobSeekerList);
        List<JobSeekerDTO> actualSearchBySkillResult = jobSeekerServiceImpl.searchBySkill("Skill");
        assertEquals(1, actualSearchBySkillResult.size());
        JobSeekerDTO getResult = actualSearchBySkillResult.get(0);
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("Doe", getResult.getLastName());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("Jane", getResult.getFirstName());
        verify(iJobSeekerDao).findBySkillContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#searchBySkill(String)}
     */
    @Test
    void testSearchBySkill3() {
        when(iJobSeekerDao.findBySkillContainingIgnoreCase((String) any()))
                .thenThrow(new InvalidJobSeekerException("Msg"));
        assertThrows(InvalidJobSeekerException.class, () -> jobSeekerServiceImpl.searchBySkill("Skill"));
        verify(iJobSeekerDao).findBySkillContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobSeekerServiceImpl#searchBySkill(String)}
     */
    @Test
    void testSearchBySkill4() throws UnsupportedEncodingException {
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
        JobSeeker jobSeeker1 = mock(JobSeeker.class);
        when(jobSeeker1.getId()).thenReturn(123L);
        when(jobSeeker1.getEmail()).thenReturn("jane.doe@example.org");
        when(jobSeeker1.getFirstName()).thenReturn("Jane");
        when(jobSeeker1.getLastName()).thenReturn("Doe");
        doNothing().when(jobSeeker1).setAppliedJobs((List<JobApplication>) any());
        doNothing().when(jobSeeker1).setAttachment((Attachment) any());
        doNothing().when(jobSeeker1).setEmail((String) any());
        doNothing().when(jobSeeker1).setFirstName((String) any());
        doNothing().when(jobSeeker1).setId((Long) any());
        doNothing().when(jobSeeker1).setLastName((String) any());
        doNothing().when(jobSeeker1).setPassword((String) any());
        doNothing().when(jobSeeker1).setSkill((String) any());
        jobSeeker1.setAppliedJobs(new ArrayList<>());
        jobSeeker1.setAttachment(attachment1);
        jobSeeker1.setEmail("jane.doe@example.org");
        jobSeeker1.setFirstName("Jane");
        jobSeeker1.setId(123L);
        jobSeeker1.setLastName("Doe");
        jobSeeker1.setPassword("iloveyou");
        jobSeeker1.setSkill("Skill");

        ArrayList<JobSeeker> jobSeekerList = new ArrayList<>();
        jobSeekerList.add(jobSeeker1);
        when(iJobSeekerDao.findBySkillContainingIgnoreCase((String) any())).thenReturn(jobSeekerList);
        List<JobSeekerDTO> actualSearchBySkillResult = jobSeekerServiceImpl.searchBySkill("Skill");
        assertEquals(1, actualSearchBySkillResult.size());
        JobSeekerDTO getResult = actualSearchBySkillResult.get(0);
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("Doe", getResult.getLastName());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("Jane", getResult.getFirstName());
        verify(iJobSeekerDao).findBySkillContainingIgnoreCase((String) any());
        verify(jobSeeker1).getId();
        verify(jobSeeker1).getEmail();
        verify(jobSeeker1).getFirstName();
        verify(jobSeeker1).getLastName();
        verify(jobSeeker1).setAppliedJobs((List<JobApplication>) any());
        verify(jobSeeker1).setAttachment((Attachment) any());
        verify(jobSeeker1).setEmail((String) any());
        verify(jobSeeker1).setFirstName((String) any());
        verify(jobSeeker1).setId((Long) any());
        verify(jobSeeker1).setLastName((String) any());
        verify(jobSeeker1).setPassword((String) any());
        verify(jobSeeker1).setSkill((String) any());
    }
}

