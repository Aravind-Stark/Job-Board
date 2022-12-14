package com.example.jobboardapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jobboardapp.dao.IJobDao;
import com.example.jobboardapp.dao.IJobSeekerDao;
import com.example.jobboardapp.dao.IRecruiterDao;
import com.example.jobboardapp.dto.JobDTO;
import com.example.jobboardapp.dto.JobListDTO;
import com.example.jobboardapp.entities.Attachment;
import com.example.jobboardapp.entities.Job;
import com.example.jobboardapp.entities.JobApplication;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.entities.Recruiter;
import com.example.jobboardapp.exceptions.InvalidJobException;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JobServiceImpl.class})
@ExtendWith(SpringExtension.class)
class JobServiceImplTest {
    @MockBean
    private IJobDao iJobDao;

    @MockBean
    private IJobSeekerDao iJobSeekerDao;

    @MockBean
    private IRecruiterDao iRecruiterDao;

    @Autowired
    private JobServiceImpl jobServiceImpl;

    /**
     * Method under test: {@link JobServiceImpl#close(Long)}
     */
    @Test
    void testClose() {
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
        Optional<Job> ofResult = Optional.of(job);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());

        Job job1 = new Job();
        job1.setCompanyName("Company Name");
        job1.setId(123L);
        job1.setJobApplications(new ArrayList<>());
        job1.setJobDescription("Job Description");
        job1.setJobTitle("Dr");
        job1.setLocation("Location");
        job1.setPostedBy(recruiter1);
        job1.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job1.setSkill("Skill");
        when(iJobDao.save((Job) any())).thenReturn(job1);
        when(iJobDao.findById((Long) any())).thenReturn(ofResult);
        when(iJobDao.existsById((Long) any())).thenReturn(true);
        jobServiceImpl.close(123L);
        verify(iJobDao).existsById((Long) any());
        verify(iJobDao).save((Job) any());
        verify(iJobDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#close(Long)}
     */
    @Test
    void testClose2() {
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
        Optional<Job> ofResult = Optional.of(job);
        when(iJobDao.save((Job) any())).thenThrow(new InvalidJobException("An error occurred"));
        when(iJobDao.findById((Long) any())).thenReturn(ofResult);
        when(iJobDao.existsById((Long) any())).thenReturn(true);
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.close(123L));
        verify(iJobDao).existsById((Long) any());
        verify(iJobDao).save((Job) any());
        verify(iJobDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#close(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testClose3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.example.jobboardapp.serviceimpl.JobServiceImpl.close(JobServiceImpl.java:37)
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(iJobDao.save((Job) any())).thenReturn(job);
        when(iJobDao.findById((Long) any())).thenReturn(Optional.empty());
        when(iJobDao.existsById((Long) any())).thenReturn(true);
        jobServiceImpl.close(123L);
    }

    /**
     * Method under test: {@link JobServiceImpl#close(Long)}
     */
    @Test
    void testClose4() {
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
        Optional<Job> ofResult = Optional.of(job);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());

        Job job1 = new Job();
        job1.setCompanyName("Company Name");
        job1.setId(123L);
        job1.setJobApplications(new ArrayList<>());
        job1.setJobDescription("Job Description");
        job1.setJobTitle("Dr");
        job1.setLocation("Location");
        job1.setPostedBy(recruiter1);
        job1.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job1.setSkill("Skill");
        when(iJobDao.save((Job) any())).thenReturn(job1);
        when(iJobDao.findById((Long) any())).thenReturn(ofResult);
        when(iJobDao.existsById((Long) any())).thenReturn(false);
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.close(123L));
        verify(iJobDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {
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
        Optional<Job> ofResult = Optional.of(job);
        when(iJobDao.findById((Long) any())).thenReturn(ofResult);
        when(iJobDao.existsById((Long) any())).thenReturn(true);
        assertSame(job, jobServiceImpl.findById(123L));
        verify(iJobDao).existsById((Long) any());
        verify(iJobDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findById(Long)}
     */
    @Test
    void testFindById2() {
        when(iJobDao.findById((Long) any())).thenThrow(new InvalidJobException("An error occurred"));
        when(iJobDao.existsById((Long) any())).thenReturn(true);
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.findById(123L));
        verify(iJobDao).existsById((Long) any());
        verify(iJobDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindById3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.example.jobboardapp.serviceimpl.JobServiceImpl.findById(JobServiceImpl.java:48)
        //   See https://diff.blue/R013 to resolve this issue.

        when(iJobDao.findById((Long) any())).thenReturn(Optional.empty());
        when(iJobDao.existsById((Long) any())).thenReturn(true);
        jobServiceImpl.findById(123L);
    }

    /**
     * Method under test: {@link JobServiceImpl#findById(Long)}
     */
    @Test
    void testFindById4() {
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
        Optional<Job> ofResult = Optional.of(job);
        when(iJobDao.findById((Long) any())).thenReturn(ofResult);
        when(iJobDao.existsById((Long) any())).thenReturn(false);
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.findById(123L));
        verify(iJobDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsBySkill(String)}
     */
    @Test
    void testFindJobsBySkill() {
        when(iJobDao.findBySkillContainingIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(jobServiceImpl.findJobsBySkill("Name").isEmpty());
        verify(iJobDao).findBySkillContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsBySkill(String)}
     */
    @Test
    void testFindJobsBySkill2() {
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

        ArrayList<Job> jobList = new ArrayList<>();
        jobList.add(job);
        when(iJobDao.findBySkillContainingIgnoreCase((String) any())).thenReturn(jobList);
        assertEquals(1, jobServiceImpl.findJobsBySkill("Name").size());
        verify(iJobDao).findBySkillContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsBySkill(String)}
     */
    @Test
    void testFindJobsBySkill3() {
        when(iJobDao.findBySkillContainingIgnoreCase((String) any()))
                .thenThrow(new InvalidJobException("An error occurred"));
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.findJobsBySkill("Name"));
        verify(iJobDao).findBySkillContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsBySkill(String)}
     */
    @Test
    void testFindJobsBySkill4() {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());
        Job job = mock(Job.class);
        when(job.getPostedBy()).thenReturn(recruiter1);
        when(job.getId()).thenReturn(123L);
        when(job.getJobDescription()).thenReturn("Job Description");
        when(job.getJobTitle()).thenReturn("Dr");
        when(job.getLocation()).thenReturn("Location");
        when(job.getSkill()).thenReturn("Skill");
        when(job.getPostedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        doNothing().when(job).setCompanyName((String) any());
        doNothing().when(job).setId((Long) any());
        doNothing().when(job).setJobApplications((List<JobApplication>) any());
        doNothing().when(job).setJobDescription((String) any());
        doNothing().when(job).setJobTitle((String) any());
        doNothing().when(job).setLocation((String) any());
        doNothing().when(job).setPostedBy((Recruiter) any());
        doNothing().when(job).setPostedDate((LocalDateTime) any());
        doNothing().when(job).setSkill((String) any());
        job.setCompanyName("Company Name");
        job.setId(123L);
        job.setJobApplications(new ArrayList<>());
        job.setJobDescription("Job Description");
        job.setJobTitle("Dr");
        job.setLocation("Location");
        job.setPostedBy(recruiter);
        job.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job.setSkill("Skill");

        ArrayList<Job> jobList = new ArrayList<>();
        jobList.add(job);
        when(iJobDao.findBySkillContainingIgnoreCase((String) any())).thenReturn(jobList);
        assertEquals(1, jobServiceImpl.findJobsBySkill("Name").size());
        verify(iJobDao).findBySkillContainingIgnoreCase((String) any());
        verify(job).getPostedBy();
        verify(job).getId();
        verify(job).getJobDescription();
        verify(job).getJobTitle();
        verify(job).getLocation();
        verify(job).getSkill();
        verify(job).getPostedDate();
        verify(job).setCompanyName((String) any());
        verify(job).setId((Long) any());
        verify(job).setJobApplications((List<JobApplication>) any());
        verify(job).setJobDescription((String) any());
        verify(job).setJobTitle((String) any());
        verify(job).setLocation((String) any());
        verify(job).setPostedBy((Recruiter) any());
        verify(job).setPostedDate((LocalDateTime) any());
        verify(job).setSkill((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#postJob(JobDTO)}
     */
    @Test
    void testPostJob() {
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
        when(iJobDao.save((Job) any())).thenReturn(job);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());
        Optional<Recruiter> ofResult = Optional.of(recruiter1);
        when(iRecruiterDao.findById((Long) any())).thenReturn(ofResult);
        when(iRecruiterDao.existsById((Long) any())).thenReturn(true);
        assertSame(job, jobServiceImpl.postJob(new JobDTO()));
        verify(iJobDao).save((Job) any());
        verify(iRecruiterDao).existsById((Long) any());
        verify(iRecruiterDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#postJob(JobDTO)}
     */
    @Test
    void testPostJob2() {
        when(iJobDao.save((Job) any())).thenThrow(new InvalidJobException("An error occurred"));

        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());
        Optional<Recruiter> ofResult = Optional.of(recruiter);
        when(iRecruiterDao.findById((Long) any())).thenReturn(ofResult);
        when(iRecruiterDao.existsById((Long) any())).thenReturn(true);
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.postJob(new JobDTO()));
        verify(iJobDao).save((Job) any());
        verify(iRecruiterDao).existsById((Long) any());
        verify(iRecruiterDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#postJob(JobDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPostJob3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.example.jobboardapp.serviceimpl.JobServiceImpl.postJob(JobServiceImpl.java:78)
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(iJobDao.save((Job) any())).thenReturn(job);
        when(iRecruiterDao.findById((Long) any())).thenReturn(Optional.empty());
        when(iRecruiterDao.existsById((Long) any())).thenReturn(true);
        jobServiceImpl.postJob(new JobDTO());
    }

    /**
     * Method under test: {@link JobServiceImpl#postJob(JobDTO)}
     */
    @Test
    void testPostJob4() {
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
        when(iJobDao.save((Job) any())).thenReturn(job);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());
        Optional<Recruiter> ofResult = Optional.of(recruiter1);
        when(iRecruiterDao.findById((Long) any())).thenReturn(ofResult);
        when(iRecruiterDao.existsById((Long) any())).thenReturn(false);
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.postJob(new JobDTO()));
        verify(iRecruiterDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#postJob(JobDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPostJob5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.jobboardapp.serviceimpl.JobServiceImpl.postJob(JobServiceImpl.java:77)
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(iJobDao.save((Job) any())).thenReturn(job);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());
        Optional<Recruiter> ofResult = Optional.of(recruiter1);
        when(iRecruiterDao.findById((Long) any())).thenReturn(ofResult);
        when(iRecruiterDao.existsById((Long) any())).thenReturn(true);
        jobServiceImpl.postJob(null);
    }

    /**
     * Method under test: {@link JobServiceImpl#postJob(JobDTO)}
     */
    @Test
    void testPostJob6() {
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
        when(iJobDao.save((Job) any())).thenReturn(job);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());
        Optional<Recruiter> ofResult = Optional.of(recruiter1);
        when(iRecruiterDao.findById((Long) any())).thenReturn(ofResult);
        when(iRecruiterDao.existsById((Long) any())).thenReturn(true);
        JobDTO jobDTO = mock(JobDTO.class);
        when(jobDTO.getJobDescription()).thenReturn("Job Description");
        when(jobDTO.getJobTitle()).thenReturn("Dr");
        when(jobDTO.getLocation()).thenReturn("Location");
        when(jobDTO.getSkill()).thenReturn("Skill");
        when(jobDTO.getPostedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(jobDTO.getRecruiterId()).thenReturn(123L);
        assertSame(job, jobServiceImpl.postJob(jobDTO));
        verify(iJobDao).save((Job) any());
        verify(iRecruiterDao).existsById((Long) any());
        verify(iRecruiterDao).findById((Long) any());
        verify(jobDTO).getJobDescription();
        verify(jobDTO).getJobTitle();
        verify(jobDTO).getLocation();
        verify(jobDTO).getSkill();
        verify(jobDTO).getPostedDate();
        verify(jobDTO, atLeast(1)).getRecruiterId();
    }

    /**
     * Method under test: {@link JobServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        when(iJobDao.findAll()).thenReturn(new ArrayList<>());
        assertTrue(jobServiceImpl.findAll().isEmpty());
        verify(iJobDao).findAll();
    }

    /**
     * Method under test: {@link JobServiceImpl#findAll()}
     */
    @Test
    void testFindAll2() {
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

        ArrayList<Job> jobList = new ArrayList<>();
        jobList.add(job);
        when(iJobDao.findAll()).thenReturn(jobList);
        assertEquals(1, jobServiceImpl.findAll().size());
        verify(iJobDao).findAll();
    }

    /**
     * Method under test: {@link JobServiceImpl#findAll()}
     */
    @Test
    void testFindAll3() {
        when(iJobDao.findAll()).thenThrow(new InvalidJobException("An error occurred"));
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.findAll());
        verify(iJobDao).findAll();
    }

    /**
     * Method under test: {@link JobServiceImpl#findAll()}
     */
    @Test
    void testFindAll4() {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());
        Job job = mock(Job.class);
        when(job.getPostedBy()).thenReturn(recruiter1);
        when(job.getId()).thenReturn(123L);
        when(job.getJobDescription()).thenReturn("Job Description");
        when(job.getJobTitle()).thenReturn("Dr");
        when(job.getLocation()).thenReturn("Location");
        when(job.getSkill()).thenReturn("Skill");
        when(job.getPostedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        doNothing().when(job).setCompanyName((String) any());
        doNothing().when(job).setId((Long) any());
        doNothing().when(job).setJobApplications((List<JobApplication>) any());
        doNothing().when(job).setJobDescription((String) any());
        doNothing().when(job).setJobTitle((String) any());
        doNothing().when(job).setLocation((String) any());
        doNothing().when(job).setPostedBy((Recruiter) any());
        doNothing().when(job).setPostedDate((LocalDateTime) any());
        doNothing().when(job).setSkill((String) any());
        job.setCompanyName("Company Name");
        job.setId(123L);
        job.setJobApplications(new ArrayList<>());
        job.setJobDescription("Job Description");
        job.setJobTitle("Dr");
        job.setLocation("Location");
        job.setPostedBy(recruiter);
        job.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job.setSkill("Skill");

        ArrayList<Job> jobList = new ArrayList<>();
        jobList.add(job);
        when(iJobDao.findAll()).thenReturn(jobList);
        assertEquals(1, jobServiceImpl.findAll().size());
        verify(iJobDao).findAll();
        verify(job).getPostedBy();
        verify(job).getId();
        verify(job).getJobDescription();
        verify(job).getJobTitle();
        verify(job).getLocation();
        verify(job).getSkill();
        verify(job).getPostedDate();
        verify(job).setCompanyName((String) any());
        verify(job).setId((Long) any());
        verify(job).setJobApplications((List<JobApplication>) any());
        verify(job).setJobDescription((String) any());
        verify(job).setJobTitle((String) any());
        verify(job).setLocation((String) any());
        verify(job).setPostedBy((Recruiter) any());
        verify(job).setPostedDate((LocalDateTime) any());
        verify(job).setSkill((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#awardJob(Long, Long)}
     */
    @Test
    void testAwardJob() throws UnsupportedEncodingException {
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
        Optional<Job> ofResult1 = Optional.of(job);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());

        Job job1 = new Job();
        job1.setCompanyName("Company Name");
        job1.setId(123L);
        job1.setJobApplications(new ArrayList<>());
        job1.setJobDescription("Job Description");
        job1.setJobTitle("Dr");
        job1.setLocation("Location");
        job1.setPostedBy(recruiter1);
        job1.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job1.setSkill("Skill");
        when(iJobDao.saveAndFlush((Job) any())).thenReturn(job1);
        when(iJobDao.findById((Long) any())).thenReturn(ofResult1);
        jobServiceImpl.awardJob(123L, 123L);
        verify(iJobSeekerDao).findById((Long) any());
        verify(iJobDao).saveAndFlush((Job) any());
        verify(iJobDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#awardJob(Long, Long)}
     */
    @Test
    void testAwardJob2() throws UnsupportedEncodingException {
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
        Optional<Job> ofResult1 = Optional.of(job);
        when(iJobDao.saveAndFlush((Job) any())).thenThrow(new InvalidJobException("An error occurred"));
        when(iJobDao.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.awardJob(123L, 123L));
        verify(iJobSeekerDao).findById((Long) any());
        verify(iJobDao).saveAndFlush((Job) any());
        verify(iJobDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#awardJob(Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAwardJob3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.example.jobboardapp.serviceimpl.JobServiceImpl.awardJob(JobServiceImpl.java:116)
        //   See https://diff.blue/R013 to resolve this issue.

        when(iJobSeekerDao.findById((Long) any())).thenReturn(Optional.empty());

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
        Optional<Job> ofResult = Optional.of(job);

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());

        Job job1 = new Job();
        job1.setCompanyName("Company Name");
        job1.setId(123L);
        job1.setJobApplications(new ArrayList<>());
        job1.setJobDescription("Job Description");
        job1.setJobTitle("Dr");
        job1.setLocation("Location");
        job1.setPostedBy(recruiter1);
        job1.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job1.setSkill("Skill");
        when(iJobDao.saveAndFlush((Job) any())).thenReturn(job1);
        when(iJobDao.findById((Long) any())).thenReturn(ofResult);
        jobServiceImpl.awardJob(123L, 123L);
    }

    /**
     * Method under test: {@link JobServiceImpl#deleteJob(Long)}
     */
    @Test
    void testDeleteJob() {
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
        Optional<Job> ofResult = Optional.of(job);
        doNothing().when(iJobDao).delete((Job) any());
        when(iJobDao.findById((Long) any())).thenReturn(ofResult);
        jobServiceImpl.deleteJob(123L);
        verify(iJobDao).findById((Long) any());
        verify(iJobDao).delete((Job) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#deleteJob(Long)}
     */
    @Test
    void testDeleteJob2() {
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
        Optional<Job> ofResult = Optional.of(job);
        doThrow(new InvalidJobException("An error occurred")).when(iJobDao).delete((Job) any());
        when(iJobDao.findById((Long) any())).thenReturn(ofResult);
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.deleteJob(123L));
        verify(iJobDao).findById((Long) any());
        verify(iJobDao).delete((Job) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#deleteJob(Long)}
     */
    @Test
    void testDeleteJob3() {
        doNothing().when(iJobDao).delete((Job) any());
        when(iJobDao.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.deleteJob(123L));
        verify(iJobDao).findById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findByRecruiterId(Long)}
     */
    @Test
    void testFindByRecruiterId() {
        ArrayList<JobListDTO> jobListDTOList = new ArrayList<>();
        when(iJobDao.findByRecruiterId((Long) any())).thenReturn(jobListDTOList);
        when(iRecruiterDao.existsById((Long) any())).thenReturn(true);
        List<JobListDTO> actualFindByRecruiterIdResult = jobServiceImpl.findByRecruiterId(123L);
        assertSame(jobListDTOList, actualFindByRecruiterIdResult);
        assertTrue(actualFindByRecruiterIdResult.isEmpty());
        verify(iJobDao).findByRecruiterId((Long) any());
        verify(iRecruiterDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findByRecruiterId(Long)}
     */
    @Test
    void testFindByRecruiterId2() {
        when(iJobDao.findByRecruiterId((Long) any())).thenThrow(new InvalidJobException("An error occurred"));
        when(iRecruiterDao.existsById((Long) any())).thenReturn(true);
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.findByRecruiterId(123L));
        verify(iJobDao).findByRecruiterId((Long) any());
        verify(iRecruiterDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findByRecruiterId(Long)}
     */
    @Test
    void testFindByRecruiterId3() {
        when(iJobDao.findByRecruiterId((Long) any())).thenReturn(new ArrayList<>());
        when(iRecruiterDao.existsById((Long) any())).thenReturn(false);
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.findByRecruiterId(123L));
        verify(iRecruiterDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsByTitle(String)}
     */
    @Test
    void testFindJobsByTitle() {
        when(iJobDao.findByJobTitleIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(jobServiceImpl.findJobsByTitle("Dr").isEmpty());
        verify(iJobDao).findByJobTitleIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsByTitle(String)}
     */
    @Test
    void testFindJobsByTitle2() {
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

        ArrayList<Job> jobList = new ArrayList<>();
        jobList.add(job);
        when(iJobDao.findByJobTitleIgnoreCase((String) any())).thenReturn(jobList);
        assertEquals(1, jobServiceImpl.findJobsByTitle("Dr").size());
        verify(iJobDao).findByJobTitleIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsByTitle(String)}
     */
    @Test
    void testFindJobsByTitle3() {
        when(iJobDao.findByJobTitleIgnoreCase((String) any())).thenThrow(new InvalidJobException("An error occurred"));
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.findJobsByTitle("Dr"));
        verify(iJobDao).findByJobTitleIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsByTitle(String)}
     */
    @Test
    void testFindJobsByTitle4() {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());
        Job job = mock(Job.class);
        when(job.getPostedBy()).thenReturn(recruiter1);
        when(job.getId()).thenReturn(123L);
        when(job.getJobDescription()).thenReturn("Job Description");
        when(job.getJobTitle()).thenReturn("Dr");
        when(job.getLocation()).thenReturn("Location");
        when(job.getSkill()).thenReturn("Skill");
        when(job.getPostedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        doNothing().when(job).setCompanyName((String) any());
        doNothing().when(job).setId((Long) any());
        doNothing().when(job).setJobApplications((List<JobApplication>) any());
        doNothing().when(job).setJobDescription((String) any());
        doNothing().when(job).setJobTitle((String) any());
        doNothing().when(job).setLocation((String) any());
        doNothing().when(job).setPostedBy((Recruiter) any());
        doNothing().when(job).setPostedDate((LocalDateTime) any());
        doNothing().when(job).setSkill((String) any());
        job.setCompanyName("Company Name");
        job.setId(123L);
        job.setJobApplications(new ArrayList<>());
        job.setJobDescription("Job Description");
        job.setJobTitle("Dr");
        job.setLocation("Location");
        job.setPostedBy(recruiter);
        job.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job.setSkill("Skill");

        ArrayList<Job> jobList = new ArrayList<>();
        jobList.add(job);
        when(iJobDao.findByJobTitleIgnoreCase((String) any())).thenReturn(jobList);
        assertEquals(1, jobServiceImpl.findJobsByTitle("Dr").size());
        verify(iJobDao).findByJobTitleIgnoreCase((String) any());
        verify(job).getPostedBy();
        verify(job).getId();
        verify(job).getJobDescription();
        verify(job).getJobTitle();
        verify(job).getLocation();
        verify(job).getSkill();
        verify(job).getPostedDate();
        verify(job).setCompanyName((String) any());
        verify(job).setId((Long) any());
        verify(job).setJobApplications((List<JobApplication>) any());
        verify(job).setJobDescription((String) any());
        verify(job).setJobTitle((String) any());
        verify(job).setLocation((String) any());
        verify(job).setPostedBy((Recruiter) any());
        verify(job).setPostedDate((LocalDateTime) any());
        verify(job).setSkill((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsByLocation(String)}
     */
    @Test
    void testFindJobsByLocation() {
        when(iJobDao.findByLocationIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(jobServiceImpl.findJobsByLocation("Location").isEmpty());
        verify(iJobDao).findByLocationIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsByLocation(String)}
     */
    @Test
    void testFindJobsByLocation2() {
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

        ArrayList<Job> jobList = new ArrayList<>();
        jobList.add(job);
        when(iJobDao.findByLocationIgnoreCase((String) any())).thenReturn(jobList);
        assertEquals(1, jobServiceImpl.findJobsByLocation("Location").size());
        verify(iJobDao).findByLocationIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsByLocation(String)}
     */
    @Test
    void testFindJobsByLocation3() {
        when(iJobDao.findByLocationIgnoreCase((String) any())).thenThrow(new InvalidJobException("An error occurred"));
        assertThrows(InvalidJobException.class, () -> jobServiceImpl.findJobsByLocation("Location"));
        verify(iJobDao).findByLocationIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link JobServiceImpl#findJobsByLocation(String)}
     */
    @Test
    void testFindJobsByLocation4() {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());

        Recruiter recruiter1 = new Recruiter();
        recruiter1.setCompanyName("Company Name");
        recruiter1.setEmail("jane.doe@example.org");
        recruiter1.setFirstName("Jane");
        recruiter1.setId(123L);
        recruiter1.setJobApplications(new ArrayList<>());
        recruiter1.setLastName("Doe");
        recruiter1.setPassword("iloveyou");
        recruiter1.setPostedJobs(new ArrayList<>());
        Job job = mock(Job.class);
        when(job.getPostedBy()).thenReturn(recruiter1);
        when(job.getId()).thenReturn(123L);
        when(job.getJobDescription()).thenReturn("Job Description");
        when(job.getJobTitle()).thenReturn("Dr");
        when(job.getLocation()).thenReturn("Location");
        when(job.getSkill()).thenReturn("Skill");
        when(job.getPostedDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        doNothing().when(job).setCompanyName((String) any());
        doNothing().when(job).setId((Long) any());
        doNothing().when(job).setJobApplications((List<JobApplication>) any());
        doNothing().when(job).setJobDescription((String) any());
        doNothing().when(job).setJobTitle((String) any());
        doNothing().when(job).setLocation((String) any());
        doNothing().when(job).setPostedBy((Recruiter) any());
        doNothing().when(job).setPostedDate((LocalDateTime) any());
        doNothing().when(job).setSkill((String) any());
        job.setCompanyName("Company Name");
        job.setId(123L);
        job.setJobApplications(new ArrayList<>());
        job.setJobDescription("Job Description");
        job.setJobTitle("Dr");
        job.setLocation("Location");
        job.setPostedBy(recruiter);
        job.setPostedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        job.setSkill("Skill");

        ArrayList<Job> jobList = new ArrayList<>();
        jobList.add(job);
        when(iJobDao.findByLocationIgnoreCase((String) any())).thenReturn(jobList);
        assertEquals(1, jobServiceImpl.findJobsByLocation("Location").size());
        verify(iJobDao).findByLocationIgnoreCase((String) any());
        verify(job).getPostedBy();
        verify(job).getId();
        verify(job).getJobDescription();
        verify(job).getJobTitle();
        verify(job).getLocation();
        verify(job).getSkill();
        verify(job).getPostedDate();
        verify(job).setCompanyName((String) any());
        verify(job).setId((Long) any());
        verify(job).setJobApplications((List<JobApplication>) any());
        verify(job).setJobDescription((String) any());
        verify(job).setJobTitle((String) any());
        verify(job).setLocation((String) any());
        verify(job).setPostedBy((Recruiter) any());
        verify(job).setPostedDate((LocalDateTime) any());
        verify(job).setSkill((String) any());
    }
}

