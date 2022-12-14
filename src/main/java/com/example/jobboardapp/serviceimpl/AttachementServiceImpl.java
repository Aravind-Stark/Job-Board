package com.example.jobboardapp.serviceimpl;

import com.example.jobboardapp.dao.IAttachmentDao;
import com.example.jobboardapp.dao.IJobSeekerDao;
import com.example.jobboardapp.entities.Attachment;
import com.example.jobboardapp.entities.JobSeeker;
import com.example.jobboardapp.exceptions.InvalidJobSeekerException;
import com.example.jobboardapp.service.IAttachementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachementServiceImpl implements IAttachementService {
    @Autowired
    IAttachmentDao attachmentDao;

    @Autowired
    IJobSeekerDao jobSeekerDao;

    @Override
    public Attachment uploadResume(MultipartFile multipartFile, Long id) throws Exception {

        if((jobSeekerDao.existsById(id)) && attachmentDao.findByJobSeekerId(id)==null){

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            Attachment attachment
                    = new Attachment(fileName,
                    multipartFile.getContentType(),
                    multipartFile.getBytes());
            JobSeeker jobSeeker = new JobSeeker();
            jobSeeker.setId(id);
            attachment.setJobSeeker(jobSeeker);

            return  attachmentDao.save(attachment);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }}
        else{
            throw new InvalidJobSeekerException("Invalid Jobseeker ID or already resume is uploaded");
        }
    }

    @Override
    public Attachment getAttachment(Long jobSeekerID) {
        if(jobSeekerDao.existsById(jobSeekerID)) {
            return attachmentDao.findByJobSeekerId(jobSeekerID);
        }else
            throw new InvalidJobSeekerException("Provide valid jobSeekerId");

    }

    @Override
    public Attachment updateResume(MultipartFile multipartFile, Long id) throws Exception {
        if(attachmentDao.findByJobSeekerId(id).getJobSeeker().getId()==id){

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            try {
                if (fileName.contains("..")) {
                    throw new Exception("Filename contains invalid path sequence "
                            + fileName);
                }

                Attachment attachment = attachmentDao.findByJobSeekerId(id);
                attachment.setFileName(fileName);
                attachment.setFileType(multipartFile.getContentType());
                attachment.setData(multipartFile.getBytes());
                return  attachmentDao.save(attachment);

            } catch (Exception e) {
                throw new Exception("Could not save File: " + fileName);
            }
        }else {

            throw new InvalidJobSeekerException("give proper jobseeker ID");
        }

    }

    @Override
    public void deleteResume(Long id) {
        Attachment attachment = attachmentDao.findByJobSeekerId(id);
        if(jobSeekerDao.existsById(id)&&attachment!=null) {
            attachmentDao.delete(attachment);
        }else {
            throw new InvalidJobSeekerException("Invalid Jobseeker id");
        }

    }


}
