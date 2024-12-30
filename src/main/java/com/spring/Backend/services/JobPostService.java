package com.spring.Backend.services;

import com.spring.Backend.dtos.JobPostDTO;
import com.spring.Backend.entities.Application;
import com.spring.Backend.entities.JobPost;
import com.spring.Backend.entities.User;
import com.spring.Backend.repositories.JobPostRepository;
import com.spring.Backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final UserRepository UserRepository;

    public JobPostDTO saveJobPost(JobPostDTO jobPostDTO) {
        JobPost jobPost = convertToJobPostEntity(jobPostDTO);
        JobPost savedJobPost = jobPostRepository.save(jobPost);
        return convertToJobPostDTO(savedJobPost);
    }

    public List<JobPostDTO> getAllJobPosts() {
        return jobPostRepository.findAll().stream()
                .map(this::convertToJobPostDTO)
                .collect(Collectors.toList());
    }
    public Optional<JobPostDTO> jobPostId(Long id) {
        Optional<JobPost> jobPost = jobPostRepository.findById(id);
        return jobPost.map(this::convertToJobPostDTO);
    }

    public void deleteJobPostById(Long id) {
        jobPostRepository.deleteById(id);
    }

    public List<Application> getApplications(Long id) {
        Optional<JobPost> jobPost = jobPostRepository.findById(id);
        if(jobPost.isPresent()){

            return jobPost.get().getApplications();
        }
        return null;
    }

    public List<JobPostDTO> getJobPostsByUserId(Integer userId) {
        return jobPostRepository.findByUserId(userId).stream()
                .map(this::convertToJobPostDTO)
                .collect(Collectors.toList());
    }


    private JobPostDTO convertToJobPostDTO(JobPost jobPost) {
        return new JobPostDTO(
                jobPost.getId(),
                jobPost.getTitle(),
                jobPost.getDescription(),
                jobPost.getPostedDate(),
                jobPost.getLocation(),
                jobPost.getUser() != null ? jobPost.getUser().getId() : null
        );
    }

    private JobPost convertToJobPostEntity(JobPostDTO jobPostDTO) {
        Optional<User> user = UserRepository.findById(jobPostDTO.getUserId());
        if(user.isPresent()){
            return new JobPost(
                    jobPostDTO.getTitle(),
                    jobPostDTO.getDescription(),
                    jobPostDTO.getLocation(),
                    user.get()
            );
        }
        return null;
    }
}
