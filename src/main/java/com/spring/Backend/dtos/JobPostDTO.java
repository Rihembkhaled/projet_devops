package com.spring.Backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostDTO {

    private Long id;
    private String title;
    private String description;
    private Date postedDate;
    private String location;
    private Integer userId;

}
