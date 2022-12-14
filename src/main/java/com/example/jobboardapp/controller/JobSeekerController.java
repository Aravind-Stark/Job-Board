package com.example.jobboardapp.controller;

import com.example.jobboardapp.dto.JobSeekerDTO;
import com.example.jobboardapp.dto.JobSeekerRegisterDTO;
import com.example.jobboardapp.dto.UserLoginDTO;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.exceptions.InvalidJobSeekerException;
import com.example.jobboardapp.exceptions.JobPortalValidationException;
import com.example.jobboardapp.service.IJobSeekerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jobSeeker")
@CrossOrigin(origins = "*")
public class JobSeekerController {

    @Autowired
    IJobSeekerService iJobSeekerService;


	/**
	 * @param JobSeeker
	 * @return Response Entity of Object type
	 * Description : This method create/register new jobSeeker
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 */
    @PostMapping("/createJobSeeker")
    @ApiOperation("This endpoint is used to create/register new jobSeeker")
    public ResponseEntity<Object> createJobSeeker(@Valid @RequestBody JobSeekerRegisterDTO jobSeekerRegisterDTO,
                                                  BindingResult bindingResult
    ) throws Exception {
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
            iJobSeekerService.createJobSeeker(jobSeekerRegisterDTO);
        } catch (InvalidJobSeekerException exception) {
            throw new InvalidJobSeekerException("One or more entered fields contain invalid objects.");
        }
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }


	/**
	 * @param id
	 * @return Response Entity of Object type
	 * Description : This method finds jobseeker by their Id
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping("/getJobSeekerById/{id}")
    @ApiOperation("This endpoint is used to finds jobseeker by their Id")
    public JobSeekerDTO getJobSeekerById(@PathVariable Long id) {
        try {
            return iJobSeekerService.getJobSeekerById(id);
        } catch (InvalidJobSeekerException exception) {
            throw new InvalidJobSeekerException("JobSeeker with given id not found.");
        }
    }

	/**
	 * @param id,JobSeeker
	 * @return Response Entity of Object type
	 * Description : This updates jobSeeker details
	 * @PutMapping: Annotation for mapping HTTP PUT requests onto specific handler methods.
	 */
    @PutMapping("/updateJobSeeker/{id}")
    @ApiOperation("This endpoint is used to update jobSeeker details")
    public ResponseEntity<Object> updateJobSeeker(@Valid
                                                  @RequestBody JobSeekerRegisterDTO jobSeekerRegisterDTO,BindingResult bindingResult,@PathVariable Long id) {
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
            iJobSeekerService.updateJobSeeker(id, jobSeekerRegisterDTO);
        } catch (InvalidJobSeekerException exception) {
            throw new InvalidJobSeekerException("JobSeeker with given id not found");
        }
        return new ResponseEntity<>("Updated JobSeeker Successfully", HttpStatus.OK);
    }

	/**
	 * @return Response Entity of Object type
	 * Description : This method finds all the JobSeeker
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping("/listAllJobSeeker")
    @ApiOperation("This endpoint is used to finds all the JobSeeker")
    public ResponseEntity<Object> listAllJobSeeker() {
        return new ResponseEntity<>(iJobSeekerService.findAllJobSeeker(), HttpStatus.OK);
    }

	/**
	 * @param skill
	 * @return Response Entity of Object type
	 * Description : This method finds jobseeker based on the skill
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping("/searchBySkill/{skill}")
    @ApiOperation("This endpoint is used to find jobseeker based on the skill")
    public ResponseEntity<List<JobSeekerDTO>> searchBySkill(@PathVariable String skill) {
        return new ResponseEntity<>(iJobSeekerService.searchBySkill(skill), HttpStatus.OK);
    }


	/**
	 * @param location
	 * @return Response Entity of Object type
	 * Description : This method used to Authenticate the user
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 */
    @PostMapping("/jobSeekerLogin")
    @ApiOperation("This endpoint is used to used to Authenticate the jobseeker")
    public ResponseEntity<String> jobSeekerLogin(@RequestBody UserLoginDTO userLoginDTO) {

        return new ResponseEntity<>(iJobSeekerService.jobSeekerLogin(userLoginDTO), HttpStatus.OK);
    }


}