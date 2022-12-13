package com.example.jobboardapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobboardapp.dto.JobSeekerDTO;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.exceptions.InvalidJobSeekerException;
import com.example.jobboardapp.exceptions.JobPortalValidationException;
import com.example.jobboardapp.service.IJobSeekerService;

@RestController
@RequestMapping("/jobSeeker")
@CrossOrigin(origins = "*")
public class JobSeekerController {

	@Autowired
    IJobSeekerService iJobSeekerService;

	
	/*****************************************************************************************
	 * Method      : addFreelancer       
	 * @param        jobSeekerDto
	 * @return       Response Entity of Object type
	 * Description : This method adds a new freelancer.
	 * @postmapping: Post mapping requests a body from the user
	 * 				 which is then persisted onto the database.
	 ****************************************************************************************/
	@PostMapping("/createJobSeeker")
	public ResponseEntity<Object> createJobSeeker(@Valid @RequestBody JobSeeker jobSeeker, BindingResult bindingResult
												  ) {
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
			iJobSeekerService.createJobSeeker(jobSeeker);
		} catch (InvalidJobSeekerException exception) {
			throw new InvalidJobSeekerException("One or more entered fields contain invalid objects.");
		}
		return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
	}
	
	/*****************************************************************************************
	 * Method      : getById       
	 * @param        Id
	 * @return       Freelancer object
	 * Description : This method fetches a freelancer based on the unique id.
	 * @getmapping : Get mapping expects a PathVariable to be passed 
	 *               which is then used to return the entity object 
	 *               that is fetched from the database.
	 ****************************************************************************************/
	@GetMapping("/getJobSeekerById/{id}")
	public JobSeekerDTO getJobSeekerById(@PathVariable Long id) {
		try {
			return iJobSeekerService.getJobSeekerById(id);
		}
		catch(InvalidJobSeekerException exception){
			throw new InvalidJobSeekerException("Freelancer with given id not found.");
		}
	}
	
	/*****************************************************************************************
	 * Method      : getByUserName       
	 * @param        userName
	 * @return       Freelancer object
	 * Description : This method fetches a freelancer based on the unique id.
	 * @getmapping : Get mapping expects a PathVariable to be passed 
	 *               which is then used to return the entity object 
	 *               that is fetched from the database.
	 ****************************************************************************************/
	
	@PutMapping("/updateJobSeeker/{id}")
	public ResponseEntity<Object> updateJobSeeker(@Valid @PathVariable Long id,
			@RequestBody JobSeeker jobSeeker) {
		try {
			iJobSeekerService.updateJobSeeker(id, jobSeeker);
		} catch (InvalidJobSeekerException exception) {
			throw new InvalidJobSeekerException("Freelancer with given id not found");
		}
		return new ResponseEntity<>("Updated Freelancer Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/listAllJobSeeker")
	public ResponseEntity<Object> listAllJobSeeker(){
		return new ResponseEntity<>(iJobSeekerService.findAllJobSeeker(), HttpStatus.OK);
	}

	@GetMapping("/searchBySkill/{skill}")
	public ResponseEntity<List<JobSeekerDTO>> searchBySkill(@PathVariable String skill){
		return new ResponseEntity<>(iJobSeekerService.searchBySkill(skill), HttpStatus.OK);
	}


}