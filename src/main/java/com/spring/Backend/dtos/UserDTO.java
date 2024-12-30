package com.spring.Backend.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String email;
}
