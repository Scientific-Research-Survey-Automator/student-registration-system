package edu.binghamton.srs.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseCredit {
    private Integer courseNo;
    private Integer credits;
}
