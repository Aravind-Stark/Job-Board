package com.example.jobboardapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jobboardapp.dao.IRecruiterDao;
import com.example.jobboardapp.dto.RecruiterDTO;
import com.example.jobboardapp.entities.Job;
import com.example.jobboardapp.entities.JobApplication;
import com.example.jobboardapp.entities.Recruiter;
import com.example.jobboardapp.exceptions.InvalidRecruiterException;

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

@ContextConfiguration(classes = {RecruiterServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RecruiterServiceImplTest {
    @MockBean
    private IRecruiterDao iRecruiterDao;

    @Autowired
    private RecruiterServiceImpl recruiterServiceImpl;

    /**
     * Method under test: {@link RecruiterServiceImpl#findById(Long)}
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
        Optional<Recruiter> ofResult = Optional.of(recruiter);
        when(iRecruiterDao.findById((Long) any())).thenReturn(ofResult);
        when(iRecruiterDao.existsById((Long) any())).thenReturn(true);
        RecruiterDTO actualFindByIdResult = recruiterServiceImpl.findById(123L);
        assertEquals("jane.doe@example.org", actualFindByIdResult.getEmail());
        assertEquals("Doe", actualFindByIdResult.getLastName());
        assertEquals(123L, actualFindByIdResult.getId().longValue());
        assertEquals("Jane", actualFindByIdResult.getFirstName());
        verify(iRecruiterDao).existsById((Long) any());
        verify(iRecruiterDao).findById((Long) any());
    }

    /**
     * Method under test: {@link RecruiterServiceImpl#findById(Long)}
     */
    @Test
    void testFindById2() {
        when(iRecruiterDao.findById((Long) any())).thenThrow(new InvalidRecruiterException("Msg"));
        when(iRecruiterDao.existsById((Long) any())).thenReturn(true);
        assertThrows(InvalidRecruiterException.class, () -> recruiterServiceImpl.findById(123L));
        verify(iRecruiterDao).existsById((Long) any());
        verify(iRecruiterDao).findById((Long) any());
    }

    /**
     * Method under test: {@link RecruiterServiceImpl#findById(Long)}
     */
    @Test
    void testFindById3() {
        Recruiter recruiter = mock(Recruiter.class);
        when(recruiter.getId()).thenReturn(123L);
        when(recruiter.getEmail()).thenReturn("jane.doe@example.org");
        when(recruiter.getFirstName()).thenReturn("Jane");
        when(recruiter.getLastName()).thenReturn("Doe");
        doNothing().when(recruiter).setCompanyName((String) any());
        doNothing().when(recruiter).setEmail((String) any());
        doNothing().when(recruiter).setFirstName((String) any());
        doNothing().when(recruiter).setId((Long) any());
        doNothing().when(recruiter).setJobApplications((List<JobApplication>) any());
        doNothing().when(recruiter).setLastName((String) any());
        doNothing().when(recruiter).setPassword((String) any());
        doNothing().when(recruiter).setPostedJobs((List<Job>) any());
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
        RecruiterDTO actualFindByIdResult = recruiterServiceImpl.findById(123L);
        assertEquals("jane.doe@example.org", actualFindByIdResult.getEmail());
        assertEquals("Doe", actualFindByIdResult.getLastName());
        assertEquals(123L, actualFindByIdResult.getId().longValue());
        assertEquals("Jane", actualFindByIdResult.getFirstName());
        verify(iRecruiterDao).existsById((Long) any());
        verify(iRecruiterDao).findById((Long) any());
        verify(recruiter).getId();
        verify(recruiter).getEmail();
        verify(recruiter).getFirstName();
        verify(recruiter).getLastName();
        verify(recruiter).setCompanyName((String) any());
        verify(recruiter).setEmail((String) any());
        verify(recruiter).setFirstName((String) any());
        verify(recruiter).setId((Long) any());
        verify(recruiter).setJobApplications((List<JobApplication>) any());
        verify(recruiter).setLastName((String) any());
        verify(recruiter).setPassword((String) any());
        verify(recruiter).setPostedJobs((List<Job>) any());
    }

    /**
     * Method under test: {@link RecruiterServiceImpl#findById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindById4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.example.jobboardapp.serviceimpl.RecruiterServiceImpl.findById(RecruiterServiceImpl.java:28)
        //   See https://diff.blue/R013 to resolve this issue.

        when(iRecruiterDao.findById((Long) any())).thenReturn(Optional.empty());
        when(iRecruiterDao.existsById((Long) any())).thenReturn(true);
        Recruiter recruiter = mock(Recruiter.class);
        when(recruiter.getId()).thenReturn(123L);
        when(recruiter.getEmail()).thenReturn("jane.doe@example.org");
        when(recruiter.getFirstName()).thenReturn("Jane");
        when(recruiter.getLastName()).thenReturn("Doe");
        doNothing().when(recruiter).setCompanyName((String) any());
        doNothing().when(recruiter).setEmail((String) any());
        doNothing().when(recruiter).setFirstName((String) any());
        doNothing().when(recruiter).setId((Long) any());
        doNothing().when(recruiter).setJobApplications((List<JobApplication>) any());
        doNothing().when(recruiter).setLastName((String) any());
        doNothing().when(recruiter).setPassword((String) any());
        doNothing().when(recruiter).setPostedJobs((List<Job>) any());
        recruiter.setCompanyName("Company Name");
        recruiter.setEmail("jane.doe@example.org");
        recruiter.setFirstName("Jane");
        recruiter.setId(123L);
        recruiter.setJobApplications(new ArrayList<>());
        recruiter.setLastName("Doe");
        recruiter.setPassword("iloveyou");
        recruiter.setPostedJobs(new ArrayList<>());
        recruiterServiceImpl.findById(123L);
    }

    /**
     * Method under test: {@link RecruiterServiceImpl#findById(Long)}
     */
    @Test
    void testFindById5() {
        Recruiter recruiter = mock(Recruiter.class);
        when(recruiter.getId()).thenReturn(123L);
        when(recruiter.getEmail()).thenReturn("jane.doe@example.org");
        when(recruiter.getFirstName()).thenReturn("Jane");
        when(recruiter.getLastName()).thenReturn("Doe");
        doNothing().when(recruiter).setCompanyName((String) any());
        doNothing().when(recruiter).setEmail((String) any());
        doNothing().when(recruiter).setFirstName((String) any());
        doNothing().when(recruiter).setId((Long) any());
        doNothing().when(recruiter).setJobApplications((List<JobApplication>) any());
        doNothing().when(recruiter).setLastName((String) any());
        doNothing().when(recruiter).setPassword((String) any());
        doNothing().when(recruiter).setPostedJobs((List<Job>) any());
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
        when(iRecruiterDao.existsById((Long) any())).thenReturn(false);
        assertThrows(InvalidRecruiterException.class, () -> recruiterServiceImpl.findById(123L));
        verify(iRecruiterDao).existsById((Long) any());
        verify(recruiter).setCompanyName((String) any());
        verify(recruiter).setEmail((String) any());
        verify(recruiter).setFirstName((String) any());
        verify(recruiter).setId((Long) any());
        verify(recruiter).setJobApplications((List<JobApplication>) any());
        verify(recruiter).setLastName((String) any());
        verify(recruiter).setPassword((String) any());
        verify(recruiter).setPostedJobs((List<Job>) any());
    }

    /**
     * Method under test: {@link RecruiterServiceImpl#getCurrentId()}
     */
    @Test
    void testGetCurrentId() {
        when(iRecruiterDao.getCurrentSeriesId()).thenReturn(123L);
        assertEquals(123L, recruiterServiceImpl.getCurrentId().longValue());
        verify(iRecruiterDao).getCurrentSeriesId();
    }

    /**
     * Method under test: {@link RecruiterServiceImpl#getCurrentId()}
     */
    @Test
    void testGetCurrentId2() {
        when(iRecruiterDao.getCurrentSeriesId()).thenThrow(new InvalidRecruiterException("Msg"));
        assertThrows(InvalidRecruiterException.class, () -> recruiterServiceImpl.getCurrentId());
        verify(iRecruiterDao).getCurrentSeriesId();
    }

}

