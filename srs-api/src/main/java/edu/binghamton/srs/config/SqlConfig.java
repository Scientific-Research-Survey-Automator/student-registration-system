package edu.binghamton.srs.config;

import edu.binghamton.srs.util.Constants;
import edu.binghamton.srs.util.ResultSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.sql.Types;

import static edu.binghamton.srs.util.Constants.*;
import static edu.binghamton.srs.util.SqlUtils.*;

@Configuration
public class SqlConfig {

    @Bean
    public SimpleJdbcCall fetchStudentsSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(FETCH_STUDENTS_SP, jdbcTemplate)
                .returningResultSet(DB_CURSOR_OUTPUT, ResultSetMapper::toStudent);
    }

    @Bean
    public SimpleJdbcCall deleteStudentSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(DELETE_STUDENT_SP, jdbcTemplate)
                .declareParameters(new SqlInOutParameter(Constants.DB_COL_B_NUMBER, Types.VARCHAR));
    }

    @Bean
    public SimpleJdbcCall fetchAllCoursesSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(FETCH_ALL_COURSES_SP, jdbcTemplate)
                .returningResultSet(DB_CURSOR_OUTPUT, ResultSetMapper::toCourse);
    }

    @Bean
    public SimpleJdbcCall fetchAllCourseCreditsSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(FETCH_ALL_COURSE_CREDITS_SP, jdbcTemplate)
                .returningResultSet(DB_CURSOR_OUTPUT, ResultSetMapper::toCourseCredit);
    }

    @Bean
    public SimpleJdbcCall fetchAllPrerequisiteCoursesSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(FETCH_ALL_PREREQUISITES_SP, jdbcTemplate)
                .returningResultSet(DB_CURSOR_OUTPUT, ResultSetMapper::toPrerequisiteCourse);
    }

    @Bean
    public SimpleJdbcCall fetchPrerequisiteCoursesForCourseSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(FETCH_PREREQUISITES_SP, jdbcTemplate)
                .declareParameters(
                        new SqlParameter(DB_COL_COURSE_NO, Types.INTEGER),
                        new SqlParameter(DB_COL_DEPT_CODE, Types.VARCHAR)
                )
                .returningResultSet(DB_CURSOR_OUTPUT, (rs, rowNum) -> rs.getString("PREQ"));
    }

    @Bean
    public SimpleJdbcCall fetchEnrollmentsSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(FETCH_ENROLLMENT_SP, jdbcTemplate)
                .returningResultSet(DB_CURSOR_OUTPUT, ResultSetMapper::toEnrollment);
    }

    @Bean
    public SimpleJdbcCall fetchStudentsEnrolledInClassSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(FETCH_STUDENTS_ENROLLED_IN_CLASS_SP, jdbcTemplate)
                .declareParameters((new SqlParameter(DB_COL_CLASSID, Types.VARCHAR)))
                .returningResultSet(DB_CURSOR_OUTPUT, ResultSetMapper::toStudentDetails);
    }

    @Bean
    public SimpleJdbcCall enrollStudentInClassSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(ENROLL_STUDENT_IN_CLASS_SP, jdbcTemplate)
                .declareParameters(
                        new SqlParameter(DB_COL_B_NUMBER, Types.VARCHAR),
                        new SqlParameter(DB_COL_CLASSID, Types.VARCHAR)
                );
    }

    @Bean
    public SimpleJdbcCall disenrollStudentInClassSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(DISENROLL_STUDENT_IN_CLASS_SP, jdbcTemplate)
                .declareParameters(
                        new SqlParameter(DB_COL_B_NUMBER, Types.VARCHAR),
                        new SqlParameter(DB_COL_CLASSID, Types.VARCHAR)
                );
    }

    @Bean
    public SimpleJdbcCall fetchClassesSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(FETCH_CLASSES_SP, jdbcTemplate)
                .returningResultSet(DB_CURSOR_OUTPUT, ResultSetMapper::toClasses);
    }

    @Bean
    public SimpleJdbcCall fetchScoresSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(FETCH_SCORE_GRADES, jdbcTemplate)
                .returningResultSet(DB_CURSOR_OUTPUT, ResultSetMapper::toScoreGrade);
    }

    @Bean
    public SimpleJdbcCall fetchLogsSp(JdbcTemplate jdbcTemplate) {
        return generateSimpleJdbcCall(FETCH_LOGS, jdbcTemplate);
    }

    /**
     * Generates an object for calling provided stored procedure
     * @param procedureName name of the procedure to execute
     * @return instance of {@link SimpleJdbcCall}
     */
    private SimpleJdbcCall generateSimpleJdbcCall(String procedureName, JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(Constants.DB_PACKAGE_NAME)
                .withProcedureName(procedureName);
    }

}
