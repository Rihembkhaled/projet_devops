package com.spring.Backend.controllers;

import com.spring.Backend.dtos.JobPostDTO;
import com.spring.Backend.entities.Application;
import com.spring.Backend.entities.JobPost;
import com.spring.Backend.services.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("jobposts")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;

    @PostMapping("/add-post")
    public ResponseEntity<JobPostDTO> createJobPost(@RequestBody JobPostDTO jobPost) {
        JobPostDTO savedJobPost = jobPostService.saveJobPost(jobPost);
        return new ResponseEntity<>(savedJobPost, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<JobPostDTO>> getAllJobPosts() {
        List<JobPostDTO> jobPosts = jobPostService.getAllJobPosts();
        return new ResponseEntity<>(jobPosts, HttpStatus.OK);
    }

    @GetMapping("/get-post/{id}")
    public ResponseEntity<JobPostDTO> getJobPostById(@PathVariable Long id) {
        Optional<JobPostDTO> jobPost = jobPostService.jobPostId(id);
        return jobPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<Void> deleteJobPost(@PathVariable Long id) {
        jobPostService.deleteJobPostById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-app/{jobPostId}")
    public ResponseEntity<List<Application>> getApplicationsByJobPostId(@PathVariable Long jobPostId) {
        List<Application> applications = jobPostService.getApplications(jobPostId);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/get-posts/{userId}")
    public ResponseEntity<List<JobPostDTO>> getJobPostByUserId(@PathVariable Integer userId) {
        List<JobPostDTO> jobPost = jobPostService.getJobPostsByUserId(userId);
        return new ResponseEntity<>(jobPost, HttpStatus.OK);
    }
}