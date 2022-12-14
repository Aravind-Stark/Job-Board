package com.example.jobboardapp.dao;

import com.example.jobboardapp.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttachmentDao extends JpaRepository<Attachment, Long> {

    Attachment findByJobSeekerId(@Param("jobSeekerID") Long jobSeekerID );
}
