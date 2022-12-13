package com.example.jobboardapp.controller;

import com.example.jobboardapp.dto.JobListDTO;
import com.example.jobboardapp.exceptions.JobPortalValidationException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobboardapp.dto.JobDTO;
import com.example.jobboardapp.exceptions.InvalidJobException;
import com.example.jobboardapp.service.IJobService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/job")
@CrossOrigin(origins = "*")
public class JobController {

	@Autowired
	IJobService jobService;


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

	@GetMapping("/deleteJob/{id}")
	public ResponseEntity<Object> deleteJob(@PathVariable Long id) {
		try {
			jobService.deleteJob(id);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("Job with given id not found");
		}
		return new ResponseEntity<>("deleted successfully", HttpStatus.OK);

	}

	@GetMapping("/findAllJobs")
	public ResponseEntity<List<JobListDTO>> findAllJobs() {
		//return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
		return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
	}
	@GetMapping("/findByRecruiterId/{id}")
	public ResponseEntity<List<JobListDTO>> findByRecruiterId(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(jobService.findByRecruiterId(id), HttpStatus.OK);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("Job with given id doesn't exist");
		}

	}

	@GetMapping(value = "/findJobsBySkill/{name}")
	public ResponseEntity<List<JobListDTO>> findJobsBySkill(@PathVariable String name) {
		try {
			return new ResponseEntity<>(jobService.findJobsBySkill(name), HttpStatus.OK);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("no job with this skill found");
		}

	}

	@GetMapping(value = "/findJobsByTitle/{title}")
	public ResponseEntity<List<JobListDTO>> findJobsByTitle(@PathVariable String title) {
		try {
			return new ResponseEntity<>(jobService.findJobsByTitle(title), HttpStatus.OK);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("no job with this skill found");
		}

	}

	@GetMapping(value = "/findJobsByLocation/{location}")
	public ResponseEntity<List<JobListDTO>> findJobsByLocation(@PathVariable String location) {
		try {
			return new ResponseEntity<>(jobService.findJobsByLocation(location), HttpStatus.OK);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("no job with this skill found");
		}

	}

	/*public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(jobService.findById(id), HttpStatus.OK);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("Job with given id doesn't exist");
		}

	}*/


	/**
	 * 
	 * @param        job
	 * @return       Response Entity of String type
	 * Description : This method closes the job module.
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 * 
	 */

	/*@GetMapping("/close/{id}")
	public ResponseEntity<Object> close(@PathVariable Long id) {
		try {
			jobService.close(id);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("Job with given id not found");
		}
		return new ResponseEntity<>("closed successfully", HttpStatus.OK);

	}*/



	/**
	 * 
	 * @param        id
	 * @return       Response Entity of Job type
	 * Description : This method finds a job by id
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 * 
	 */

	/*@GetMapping(value = "/findById/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(jobService.findById(id), HttpStatus.OK);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("Job with given id doesn't exist");
		}

	}*/

	/**
	 * 
	 * @param        name
	 * @return       Response Entity of Object type
	 * Description : This method finds a job by Skill
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 * 
	 */

	/*@GetMapping(value = "/findJobsBySkill/{name}")
	@ResponseBody
	public ResponseEntity<Object> findbyskill(@PathVariable String name) {
		try {
			return new ResponseEntity<>(jobService.findJobsBySkillName(name), HttpStatus.OK);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("no job with this skill found");
		}

	}*/

	/**
	 * 
	 * @param        jobDto
	 * @return       Response Entity of Object type
	 * Description : This method posts a new job.
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 * 
	 */



/*	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
	}*/

	/*@PutMapping("/awardJob/{jobId}/{jobSeekerId}")
	public ResponseEntity<String> awardJob(@PathVariable Long jobId, @PathVariable Long jobSeekerId) {
		jobService.awardJob(jobId, jobSeekerId);
		String response = "Job Awarded Successfully";
		return new ResponseEntity<>(response, HttpStatus.OK);
	}*/
	
	/*@GetMapping("/getAllActive")
	public ResponseEntity<Object> getAllActiveJobs() {
		return new ResponseEntity<>(jobService.findAllActiveJobs(), HttpStatus.OK);
		
	}
*/
}
