package com.example.jobboardapp.service;

import com.example.jobboardapp.entities.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface IAttachementService {
    Attachment uploadResume(MultipartFile multipartFile, Long id) throws Exception;

    Attachment getAttachment(Long jobSeekerID);

    Attachment updateResume(MultipartFile multipartFile, Long id) throws Exception;

    void deleteResume(Long id);
}
