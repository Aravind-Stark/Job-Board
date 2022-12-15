package com.example.jobboardapp.controller;

import com.example.jobboardapp.entities.Attachment;
import com.example.jobboardapp.service.IAttachementService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/attachment")
@CrossOrigin(origins = "*")
public class AttachmentController {

    @Autowired
    IAttachementService attachementService;


    /**
     *
     * @param        id
     * @return       Response Entity of Object type
     * Description : This method uploads user resume
     * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
     *
     */
    @PostMapping("/uploadResume/{id}")
    @ApiOperation("This endpoint is used to uploads user resume")
    public ResponseEntity<Object> uploadResume(@RequestPart("file") MultipartFile multipartFile,
                                               @PathVariable Long id
    ) throws Exception {
        Attachment attachment = attachementService.uploadResume(multipartFile, id);

        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    /**
     *
     * @param        id
     * @return       Response Entity of Object type
     * Description : This method updates user resume by reuploading
     * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
     *
     */
    @PostMapping("/updateResume/{id}")
    @ApiOperation("This endpoint is used to updates user resume by reuploading")
    public ResponseEntity<Object> updateResume(@RequestParam("file") MultipartFile multipartFile,
                                               @PathVariable Long id
    ) throws Exception {
        Attachment attachment = attachementService.updateResume(multipartFile, id);

        return new ResponseEntity<>("Updated successfully", HttpStatus.CREATED);
    }

    /**
     *
     * @param        id
     * @return       Response Entity of Object type
     * Description : This method downloads resume using jobseeker id
     * @GetMapping: Annotation for mapping HTTP Get requests onto specific handler methods.
     *
     */
    @GetMapping("/download/{jobSeekerID}")
    @ApiOperation("This endpoint is used to downloads resume using jobseeker id")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long jobSeekerID) throws Exception {
        Attachment attachment = null;
        attachment = attachementService.getAttachment(jobSeekerID);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

    /**
     *
     * @param        id
     * @return       Response Entity of Object type
     * Description : This method finds resume using jobseeker id
     * @DeleteMapping: Annotation for mapping HTTP DELETE requests onto specific handler methods.
     *
     */
    @DeleteMapping("/deleteResume/{id}")
    @ApiOperation("This endpoint is used to find resume using jobseeker id")
    public ResponseEntity<Object> deleteResume(@PathVariable Long id) throws Exception {
        attachementService.deleteResume(id);

        return new ResponseEntity<>("deleted successfully", HttpStatus.CREATED);
    }

}
