package edu.binghamton.srs.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Student extends StudentDetails {

    private String stLevel;
    private Double gpa;
    private String email;
    private LocalDate birthDate;

}
