package com.spring.Backend.services;

import com.spring.Backend.dtos.ApplicationDTO;
import com.spring.Backend.entities.Application;
import com.spring.Backend.entities.JobPost;
import com.spring.Backend.repositories.ApplicationRepository;
import com.spring.Backend.repositories.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobPostRepository jobPostRepository;

    public ApplicationDTO saveApplication(Long jobPostId, ApplicationDTO applicationDTO) {
        Optional<JobPost> jobPost = jobPostRepository.findById(jobPostId);
        if (jobPost.isPresent()) {
            Application application = convertToEntity(applicationDTO);
            application.setJobPost(jobPost.get());
            jobPost.get().getApplications().add(application);

            Application savedApplication = applicationRepository.save(application);
            return convertToDTO(savedApplication);
        } else {
            throw new RuntimeException("JobPost with ID " + jobPostId + " not found.");
        }
    }
    public ApplicationDTO updateApplication(ApplicationDTO applicationDTO) {
        Application application = convertToEntity(applicationDTO);
        Application updatedApplication = applicationRepository.save(application);
        return convertToDTO(updatedApplication);
    }

    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ApplicationDTO> getApplicationById(Long id) {
        Optional<Application> application = applicationRepository.findById(id);
        return application.map(this::convertToDTO); // Convert Entity to DTO if found
    }

    public void deleteApplicationById(Long id) {
        applicationRepository.deleteById(id);
    }

    private ApplicationDTO convertToDTO(Application application) {
        return new ApplicationDTO(
                application.getId(),
                application.getApplicantName(),
                application.getEmail(),
                application.getPhoneNumber(),
                application.getSkills(),
                application.getCoverLetter(),
                application.getJobPost() != null ? application.getJobPost().getId() : null
        );
    }

    private Application convertToEntity(ApplicationDTO applicationDTO) {
        Application application = new Application();
        application.setApplicantName(applicationDTO.getApplicantName());
        application.setEmail(applicationDTO.getEmail());
        application.setPhoneNumber(applicationDTO.getPhoneNumber());
        application.setSkills(applicationDTO.getSkills());
        application.setCoverLetter(applicationDTO.getCoverLetter());

        // Retrieve the JobPost based on the jobPostId in the DTO
        Optional<JobPost> jobPost = jobPostRepository.findById(applicationDTO.getJobPostId());
        jobPost.ifPresent(application::setJobPost);

        return application;
    }
}