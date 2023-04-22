package edu.binghamton.srs.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class Course {
    private final String deptCode;
    private final Integer courseNo;
    @EqualsAndHashCode.Exclude
    private String title;
    @EqualsAndHashCode.Exclude
    private Integer credits;
}
