package com.example.jobboardapp.controller;

import com.example.jobboardapp.dto.RecruiterDTO;
import com.example.jobboardapp.dto.RecruiterRegisterDTO;
import com.example.jobboardapp.dto.UserLoginDTO;
import com.example.jobboardapp.exceptions.InvalidRecruiterException;
import com.example.jobboardapp.exceptions.JobPortalValidationException;
import com.example.jobboardapp.service.IRecruiterService;
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
@RequestMapping("/api/v1/recruiter")
@CrossOrigin(origins = "*")
public class RecruiterController {

    @Autowired
    IRecruiterService recruiterService;

	/**
	 * @param RecruiterRegisterDTO
	 * @return Response Entity of Object type
	 * Description : This method create/register new Recruiter
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 */
    @PostMapping("/createRecruiter")
    @ApiOperation("This endpoint is used to create/register new Recruiter")
    public ResponseEntity<Object> createRecruiter(@Valid @RequestBody RecruiterRegisterDTO recruiterRegisterDTO,
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
            recruiterService.save(recruiterRegisterDTO);
        } catch (InvalidRecruiterException exception) {
            throw new InvalidRecruiterException("One or more entered fields contain invalid objects.");
        }
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }
	/**
	 * @param id
	 * @return RecruiterDTO
	 * Description : This method finds recruiter by their id.
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 */
    @GetMapping("/getRecruiterById/{id}")
    @ApiOperation("This endpoint is used to finds recruiter by their id")
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
    @ApiOperation("This endpoint is used to update recruiters details")
    public ResponseEntity<Object> updateRecruiter(@Valid @PathVariable Long id,
                                                  @RequestBody RecruiterRegisterDTO recruiterRegisterDTO,BindingResult bindingResult) {
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
            recruiterService.update(id, recruiterRegisterDTO);
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
    @ApiOperation("This endpoint is used to Authenticate the recruiter")
    public ResponseEntity<String> recruiterLogin(@RequestBody UserLoginDTO userLoginDTO) {

        return new ResponseEntity<>(recruiterService.recruiterLogin(userLoginDTO), HttpStatus.OK);
    }

}