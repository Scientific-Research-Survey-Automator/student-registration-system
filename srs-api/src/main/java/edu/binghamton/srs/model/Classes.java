package edu.binghamton.srs.model;

import lombok.Builder;
import lombok.Data;

import java.time.Year;

@Data
@Builder
public class Classes {
    private String classId;
    private Course course;
    private int section;
    private Year year;
    private String semester;
    private int limit;
    private int size;
    private String room;
}
