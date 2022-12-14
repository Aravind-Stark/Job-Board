package com.example.jobboardapp.controller;

import com.example.jobboardapp.dto.RecruiterDTO;
import com.example.jobboardapp.dto.UserLoginDTO;
import com.example.jobboardapp.entities.Recruiter;
import com.example.jobboardapp.exceptions.InvalidRecruiterException;
import com.example.jobboardapp.exceptions.JobPortalValidationException;
import com.example.jobboardapp.service.IRecruiterService;
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
@RequestMapping("/recruiter")
@CrossOrigin(origins = "*")
public class RecruiterController {

    @Autowired
    IRecruiterService recruiterService;

	/**
	 * @param Recruiter
	 * @return Response Entity of Object type
	 * Description : This method create/register new Recruiter
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 */
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
	/**
	 * @param id
	 * @return RecruiterDTO
	 * Description : This method finds recruiter by their isd.
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping("/getRecruiterById/{id}")
    public RecruiterDTO getRecruiterById(@PathVariable Long id) {
        try {
            return recruiterService.findById(id);
        } catch (InvalidRecruiterException exception) {
            throw new InvalidRecruiterException("Recruiter with given id not found");
        }
    }

	/**
	 * @param id,Recruiter
	 * @return Response Entity of Object type
	 * Description : This method update recruiters details
	 * @PutMapping: Annotation for mapping HTTP PUT requests onto specific handler methods.
	 */
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

	/**
	 * @param UserLoginDTO
	 * @return Response Entity of String type
	 * Description : TThis method used to Authenticate the user
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 */
    @PostMapping("/recruiterLogin")
    public ResponseEntity<String> recruiterLogin(@RequestBody UserLoginDTO userLoginDTO) {

        return new ResponseEntity<>(recruiterService.recruiterLogin(userLoginDTO), HttpStatus.OK);
    }

}