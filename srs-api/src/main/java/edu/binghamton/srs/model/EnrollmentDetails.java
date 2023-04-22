package edu.binghamton.srs.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentDetails {
    private Student student;
    private Classes classes;
    private Double score;
    private String grade;
}
