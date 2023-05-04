package edu.binghamton.srs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class StudentDetails {
    private String bNumber;
    private String firstName;
    private String lastName;

}
