package com.spring.Backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;
    private String email;
    private String phoneNumber;
    private String skills;
    private String coverLetter;

    @ManyToOne
    private JobPost jobPost;
}
