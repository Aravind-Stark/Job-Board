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

import com.example.jobboardapp.dto.RecruiterDTO;
import com.example.jobboardapp.entities.Recruiter;
import com.example.jobboardapp.exceptions.InvalidRecruiterException;
import com.example.jobboardapp.exceptions.JobPortalValidationException;
import com.example.jobboardapp.service.IRecruiterService;

@RestController
@RequestMapping("/recruiter")
@CrossOrigin(origins = "*")
public class RecruiterController {

	@Autowired
	IRecruiterService recruiterService;

	@PostMapping("/createRecruiter")
	public ResponseEntity<Object> createRecruiter(@Valid @RequestBody Recruiter recruiter,
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
			recruiterService.save(recruiter);
		} catch (InvalidRecruiterException exception) {
			throw new InvalidRecruiterException("One or more entered fields contain invalid objects.");
		}
		return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
	}

	@GetMapping("/getRecruiterById/{id}")
	public RecruiterDTO getRecruiterById(@PathVariable Long id) {
		try {
			return recruiterService.findById(id);
		} catch (InvalidRecruiterException exception) {
			throw new InvalidRecruiterException("Recruiter with given id not found");
		}
	}

	@PutMapping("/updateRecruiter/{id}")
	public ResponseEntity<Object> updateRecruiter(@Valid @PathVariable Long id,
			@RequestBody Recruiter recruiter) {
		try {
			recruiterService.update(id, recruiter);
		} catch (InvalidRecruiterException exception) {
			throw new InvalidRecruiterException("Recruiter with given id not found");
		}
		return new ResponseEntity<>("Updated Recruiter Successfully", HttpStatus.OK);
	}
	
	/*@GetMapping("/getAllRecruiter")
	public ResponseEntity<Object> findAllRecruiter(){
		return new ResponseEntity<>(recruiterService.findAll(), HttpStatus.OK);
	}*/

}