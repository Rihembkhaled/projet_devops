package com.spring.Backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = "posted_date")
    private Date postedDate = new Date();

    private String location;

    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;

    @ManyToOne
    private User user;

    public JobPost(String title, String description, String location, User user) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.user = user;
    }
}
