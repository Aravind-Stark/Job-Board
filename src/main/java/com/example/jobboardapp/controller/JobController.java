package com.example.jobboardapp.controller;

import com.example.jobboardapp.dto.JobDTO;
import com.example.jobboardapp.dto.JobListDTO;
import com.example.jobboardapp.exceptions.InvalidJobException;
import com.example.jobboardapp.exceptions.JobPortalValidationException;
import com.example.jobboardapp.service.IJobService;
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
@RequestMapping("/job")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    IJobService jobService;


    /**
     * @RequestBody JobDTO
     * @return Response Entity of Object type
     * Description : This method Posts/create job in the Job table
     * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
     */
    @PostMapping("/postJob")
    public ResponseEntity<Object> postJob(@Valid @RequestBody JobDTO jobDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Some errors exist!");
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            List<String> errMessages = new ArrayList<>();
            for (FieldError fe : fieldErrors) {
                errMessages.add(fe.getDefaultMessage());
            }
            throw new JobPortalValidationException(errMessages);
        }
        jobService.postJob(jobDto);
        return new ResponseEntity<>("Job Posted Successfully", HttpStatus.OK);
    }

	/**
	 * @RequestBody JobDTO
	 * @return Response Entity of Object type
	 * Description : This method updates job in the Job table
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 */
    @PostMapping("/updateJob")
    public ResponseEntity<Object> updateJob(@Valid @RequestBody JobDTO jobDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Some errors exist!");
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            List<String> errMessages = new ArrayList<>();
            for (FieldError fe : fieldErrors) {
                errMessages.add(fe.getDefaultMessage());
            }
            throw new JobPortalValidationException(errMessages);
        }
        jobService.updateJob(jobDto);
        return new ResponseEntity<>("Job updated Successfully", HttpStatus.OK);
    }

	/**
	 * @param id
	 * @return Response Entity of Object type
	 * Description : This method deletes job in the Job table
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping("/deleteJob/{id}")
    public ResponseEntity<Object> deleteJob(@PathVariable Long id) {
        try {
            jobService.deleteJob(id);
        } catch (InvalidJobException exception) {
            throw new InvalidJobException("Job with given id not found");
        }
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);

    }

	/**
	 * @return Response Entity of Object type
	 * Description : This method gets all the job in the Job table
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping("/findAllJobs")
    public ResponseEntity<List<JobListDTO>> findAllJobs() {
        //return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

	/**
	 * @param id
	 * @return Response Entity of Object type
	 * Description : This method gets job posted recuiter in the Job table
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping("/findByRecruiterId/{id}")
    public ResponseEntity<List<JobListDTO>> findByRecruiterId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(jobService.findByRecruiterId(id), HttpStatus.OK);
        } catch (InvalidJobException exception) {
            throw new InvalidJobException("Job with given id doesn't exist");
        }

    }

	/**
	 * @param name
	 * @return Response Entity of Object type
	 * Description : This method finds job based on skill from the Job table
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping(value = "/findJobsBySkill/{name}")
    public ResponseEntity<List<JobListDTO>> findJobsBySkill(@PathVariable String name) {
        try {
            return new ResponseEntity<>(jobService.findJobsBySkill(name), HttpStatus.OK);
        } catch (InvalidJobException exception) {
            throw new InvalidJobException("no job with this skill found");
        }

    }

	/**
	 * @param title
	 * @return Response Entity of Object type
	 * Description : This method finds job based on skill from the Job table
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping(value = "/findJobsByTitle/{title}")
    public ResponseEntity<List<JobListDTO>> findJobsByTitle(@PathVariable String title) {
        try {
            return new ResponseEntity<>(jobService.findJobsByTitle(title), HttpStatus.OK);
        } catch (InvalidJobException exception) {
            throw new InvalidJobException("no job with this skill found");
        }

    }

	/**
	 * @param location
	 * @return Response Entity of Object type
	 * Description : This method finds job based on location from the Job table
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping(value = "/findJobsByLocation/{location}")
    public ResponseEntity<List<JobListDTO>> findJobsByLocation(@PathVariable String location) {
        try {
            return new ResponseEntity<>(jobService.findJobsByLocation(location), HttpStatus.OK);
        } catch (InvalidJobException exception) {
            throw new InvalidJobException("no job with this skill found");
        }

    }

}
