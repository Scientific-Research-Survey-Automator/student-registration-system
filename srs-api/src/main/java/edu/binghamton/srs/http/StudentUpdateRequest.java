package edu.binghamton.srs.http;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentUpdateRequest {
    private String firstName;
    private String lastName;
    private String stLevel;
    private Double gpa;
    private LocalDate birthDate;
}
