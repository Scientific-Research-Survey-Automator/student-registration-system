package edu.binghamton.srs.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Student {

    private String bNumber;
    private String firstName;
    private String lastName;
    private String stLevel;
    private Double gpa;
    private String email;
    private LocalDate birthDate;

}
