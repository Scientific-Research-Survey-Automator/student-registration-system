package edu.binghamton.srs.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrerequisiteCourse {
    private String deptCode;
    private int courseNo;
    private String preDeptCode;
    private int preCourseNo;
}
