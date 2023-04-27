package edu.binghamton.srs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class StudentDetails {
    private String bNumber;
    private String firstName;
    private String lastName;

}
