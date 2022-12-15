package com.example.jobboardapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.jobboardapp.dto.JobApplicationStatusDTO;
import com.example.jobboardapp.dto.JobApplicationsListDTO;
import com.example.jobboardapp.dto.JobSeekerDetailsDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobboardapp.dto.JobApplicationDTO;
import com.example.jobboardapp.exceptions.InvalidJobApplicationException;
import com.example.jobboardapp.exceptions.InvalidJobException;
import com.example.jobboardapp.exceptions.JobPortalValidationException;
import com.example.jobboardapp.service.IJobApplicationService;


@RestController
@RequestMapping(value = "/api/v1/jobApplication")
@CrossOrigin(origins = "*")
public class JobApplicationController {

	@Autowired
	IJobApplicationService jobApplicationService;

	/**
	 * @Param jobApplicationDto
	 * @return Response Entity of List<JobApplicationsListDTO>
	 * Description : This method creates a new Job Application
	 * @postmapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 */
	@PostMapping(value = "/apply")
	@ApiOperation("This endpoint is used to create a new Job Application")
	public ResponseEntity<String> applyToJob(@Valid @RequestBody JobApplicationDTO jobApplicationDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some errors exist!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			List<String> errMessages = new ArrayList<>();
			for (FieldError fe : fieldErrors) {
				errMessages.add(fe.getDefaultMessage());
			}
			throw new JobPortalValidationException(errMessages);
		}
		try {
			jobApplicationService.applyToJob(jobApplicationDto);
		} catch (InvalidJobException e) {
			throw new InvalidJobException("Job not found With given Id");
		} catch (InvalidJobApplicationException exception) {
			throw new InvalidJobApplicationException("One or more entered fields contain invalid objects.");
		}
		return new ResponseEntity<>("Job Applied Successfully", HttpStatus.CREATED);
	}

	/**
	 * @Param jobSeekerID
	 * @return Response Entity of List<JobApplicationsListDTO>
	 * Description : This method finds all the jobs applied by jobSeeker
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
	@GetMapping(value = "/findJobSeekersApplications/{jobSeekerID}")
	@ApiOperation("This endpoint is used to find all the jobs applied by jobSeeker")
	public ResponseEntity<List<JobApplicationsListDTO>> findJobSeekersApplications(@PathVariable Long jobSeekerID) {
		return new ResponseEntity<>(jobApplicationService.findJobSeekersApplications(jobSeekerID), HttpStatus.OK);
	}

	/**
	 * @Param recruiterId
	 * @return Response Entity of List<JobSeekerDetailsDTO>
	 * Description : This method used to find all applications for the posted jobs by recruiter
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
	@GetMapping(value = "/viewAllApplicationsByRecruiterId/{recruiterId}")
	@ApiOperation("This endpoint is used to find all applications for the posted jobs by recruiter")
	public ResponseEntity<List<JobSeekerDetailsDTO>> viewAllApplicationsByRecruiterId(@PathVariable Long recruiterId) {
		return new ResponseEntity<>(jobApplicationService.viewAllApplicationsByRecruiterId(recruiterId), HttpStatus.OK);
	}


	/**
	 * @Param id,JobApplicationDTO
	 * @return Response Entity of Object type
	 * Description : This is method is used to update the status of Job application by recruiter
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
	@PutMapping(value = "/update/{id}")
	@ApiOperation("This endpoint is used to update the status of Job application by recruiter")
	public ResponseEntity<Object> updateJobApplicationStatus(@Valid
													   @RequestBody JobApplicationStatusDTO jobApplicationStatusDTO, BindingResult bindingResult, @PathVariable Long id) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some errors exist!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			List<String> errMessages = new ArrayList<>();
			for (FieldError fe : fieldErrors) {
				errMessages.add(fe.getDefaultMessage());
			}
			throw new JobPortalValidationException(errMessages);
		}
		try {
			jobApplicationService.updateJobApplication(id, jobApplicationStatusDTO);
			return new ResponseEntity<>("job application updated successfully", HttpStatus.OK);
		} catch (InvalidJobApplicationException e) {
			throw new InvalidJobApplicationException("Invalid Job Application Id");
		}
	}

}