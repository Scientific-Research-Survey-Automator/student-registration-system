package edu.binghamton.srs.dao;

import edu.binghamton.srs.model.*;
import edu.binghamton.srs.util.ResultSetMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

import static edu.binghamton.srs.util.Constants.*;
import static edu.binghamton.srs.util.SqlUtils.*;

@Repository
public class EnrollmentDao {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcCall fetchEnrollmentsSp;
    private final SimpleJdbcCall fetchStudentsEnrolledInClassSp;
    private final SimpleJdbcCall enrollStudentInClassSp;
    private final SimpleJdbcCall disenrollStudentInClassSp;

    private final SimpleJdbcCall fetchClassesSp;
    private final SimpleJdbcCall fetchScoresSp;
    private final SimpleJdbcCall fetchLogsSp;

    public EnrollmentDao(
            JdbcTemplate jdbcTemplate,
            @Qualifier("fetchEnrollmentsSp") SimpleJdbcCall fetchEnrollmentsSp,
            @Qualifier("fetchStudentsEnrolledInClassSp") SimpleJdbcCall fetchStudentsEnrolledInClassSp,
            @Qualifier("enrollStudentInClassSp") SimpleJdbcCall enrollStudentInClassSp,
            @Qualifier("disenrollStudentInClassSp") SimpleJdbcCall disenrollStudentInClassSp,
            @Qualifier("fetchClassesSp") SimpleJdbcCall fetchClassesSp,
            @Qualifier("fetchScoresSp") SimpleJdbcCall fetchScoresSp,
            @Qualifier("fetchLogsSp") SimpleJdbcCall fetchLogsSp
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.fetchEnrollmentsSp = fetchEnrollmentsSp;
        this.fetchStudentsEnrolledInClassSp = fetchStudentsEnrolledInClassSp;
        this.enrollStudentInClassSp = enrollStudentInClassSp;
        this.disenrollStudentInClassSp = disenrollStudentInClassSp;
        this.fetchClassesSp = fetchClassesSp;
        this.fetchScoresSp = fetchScoresSp;
        this.fetchLogsSp = fetchLogsSp;
    }

    public Collection<EnrollmentDetails> search(String condition) {
        StringBuilder sqlBuilder = new StringBuilder().append(FETCH_ENROLLMENT_DETAILS);
        if (StringUtils.hasLength(condition))
            sqlBuilder.append(WHERE_CLAUSE.replace(":clause", condition));
        return jdbcTemplate.query(sqlBuilder.toString(), ResultSetMapper::toEnrollmentDetails);
    }

    public Collection<StudentDetails> fetchStudentDetailsInAClass(String classId) {
        return fetchStudentsEnrolledInClassSp.executeObject(ArrayList.class, new MapSqlParameterSource(DB_COL_CLASSID, classId));
    }

    public void enrollStudent(String bNumber, String classId) {
        SqlParameterSource sqlParams = new MapSqlParameterSource()
                .addValue(DB_COL_B_NUMBER, bNumber)
                .addValue(DB_COL_CLASSID, classId);
        enrollStudentInClassSp.execute(sqlParams);
    }

    public void disenrollStudent(String bNumber, String classId) {
        SqlParameterSource sqlParams = new MapSqlParameterSource()
                .addValue(DB_COL_B_NUMBER, bNumber)
                .addValue(DB_COL_CLASSID, classId);
        disenrollStudentInClassSp.execute(sqlParams);
    }

    public Collection<Enrollment> fetchEnrollments() {
        return fetchEnrollmentsSp.executeObject(ArrayList.class);
    }

    public Collection<Classes> fetchClasses() {
        return fetchClassesSp.executeObject(ArrayList.class);
    }

    public Collection<ScoreGrade> fetchScoreGrades() {
        return fetchScoresSp.executeObject(ArrayList.class);
    }

    public Collection<Object> fetchLogs() {
        return fetchLogsSp.executeObject(ArrayList.class);
    }
}
