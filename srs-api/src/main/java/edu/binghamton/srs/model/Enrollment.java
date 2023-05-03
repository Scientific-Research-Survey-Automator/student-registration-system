package edu.binghamton.srs.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Enrollment {
    private String bNumber;
    private String classId;
    private Double score;
}
