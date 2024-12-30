package com.spring.Backend.controllers;
import com.spring.Backend.dtos.ApplicationDTO;
import com.spring.Backend.entities.Application;
import com.spring.Backend.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/add-app/{jobPostId}")
    public ResponseEntity<ApplicationDTO> createApplication(
            @PathVariable Long jobPostId,
            @RequestBody ApplicationDTO application) {
        ApplicationDTO savedApplication = applicationService.saveApplication(jobPostId, application);
        return new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-app")
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        List<ApplicationDTO> applications = applicationService.getAllApplications();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/get-app/{id}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable Long id) {
        Optional<ApplicationDTO> application = applicationService.getApplicationById(id);
        return application.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete-app/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplicationById(id);
        return ResponseEntity.noContent().build();
    }
}
