package com.spring.Backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {

    private Long id;
    private String applicantName;
    private String email;
    private String phoneNumber;
    private String skills;
    private String coverLetter;
    private Long jobPostId;

}
