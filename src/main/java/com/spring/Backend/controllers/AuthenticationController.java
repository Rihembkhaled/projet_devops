package com.spring.Backend.controllers;

import com.spring.Backend.services.AuthenticationService;
import com.spring.Backend.dtos.RegistrationRequest;
import com.spring.Backend.dtos.AuthenticationRequest;
import com.spring.Backend.dtos.AuthenticationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
    ) throws MessagingException {
        service.register(request);
        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/secure-data")
    public ResponseEntity<String> getSecureData() {
        return ResponseEntity.ok("This is secured data for ADMIN only.");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }



}