package com.spring.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record ServiceAReq(
        String name,
        String description,
        double price,
        Integer capacity,
        @JsonFormat(pattern = "yyyy-MM-dd")
        List<LocalDate> availability

){


}