package com.spring.Backend.repositories;

import com.spring.Backend.entities.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    List<JobPost> findByUserId(Integer user_id);
}
