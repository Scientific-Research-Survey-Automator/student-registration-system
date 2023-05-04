package edu.binghamton.srs.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Student extends StudentDetails {

    private String stLevel;
    private Double gpa;
    private String email;
    private LocalDate birthDate;

}
