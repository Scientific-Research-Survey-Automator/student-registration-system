package edu.binghamton.srs.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreGrade {
    private double score;
    private String lgrade;
}
