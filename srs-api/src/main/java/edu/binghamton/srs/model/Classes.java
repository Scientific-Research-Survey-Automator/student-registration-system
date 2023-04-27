package edu.binghamton.srs.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Year;

@Data
@SuperBuilder
public class Classes extends Course {
    private String classId;
    private int section;
    private Year year;
    private String semester;
    private int limit;
    private int size;
    private String room;
}
