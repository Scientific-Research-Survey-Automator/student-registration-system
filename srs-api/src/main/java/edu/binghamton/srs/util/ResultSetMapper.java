package edu.binghamton.srs.util;

import edu.binghamton.srs.model.Course;
import edu.binghamton.srs.model.PrerequisiteCourse;
import edu.binghamton.srs.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetMapper {

    private ResultSetMapper() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultSetMapper.class);

    public static Student toStudent(ResultSet resultSet, int rowNum) throws SQLException {
        LOGGER.trace("Mapping row no: {} to student object...", rowNum);
        Student student = Student.builder()
                .bNumber(resultSet.getString(Constants.DB_COL_B_NUMBER))
                .firstName(resultSet.getString(Constants.DB_COL_FIRST_NAME))
                .lastName(resultSet.getString(Constants.DB_COL_LAST_NAME))
                .stLevel(resultSet.getString(Constants.DB_COL_ST_LEVEL))
                .gpa(resultSet.getDouble(Constants.DB_COL_GPA))
                .email(resultSet.getString(Constants.DB_COL_EMAIL))
                .birthDate(resultSet.getDate(Constants.DB_COL_BDATE).toLocalDate())
                .build();
        LOGGER.trace("Mapped row to student object: {}.", student);
        return student;
    }

    public static Course toCourse(ResultSet resultSet, int rowNum) throws SQLException {
        LOGGER.trace("Mapping row no: {} to course object...", rowNum);
        Course course = Course.builder()
                .deptCode(resultSet.getString(Constants.DB_COL_DEPT_CODE))
                .courseNo(resultSet.getInt(Constants.DB_COL_COURSE_NO))
                .title(resultSet.getString(Constants.DB_COL_TITLE))
                .credits(resultSet.getInt(Constants.DB_COL_CREDITS))
                .build();
        LOGGER.trace("Mapped row to course object: {}.", course);
        return course;
    }

    public static PrerequisiteCourse toPrerequisiteCourse(ResultSet resultSet, int rowNum) throws SQLException {
        LOGGER.trace("Mapping row no: {} to prerequisiteCourse object...", rowNum);
        PrerequisiteCourse prerequisiteCourse = PrerequisiteCourse.builder()
                .deptCode(resultSet.getString(Constants.DB_COL_DEPT_CODE))
                .courseNo(resultSet.getInt(Constants.DB_COL_COURSE_NO))
                .preDeptCode(resultSet.getString(Constants.DB_COL_PRE_DEPT_CODE))
                .preCourseNo(resultSet.getInt(Constants.DB_COL_PRE_COURSE_NO))
                .build();
        LOGGER.trace("Mapped row to prerequisiteCourse object: {}.", prerequisiteCourse);
        return prerequisiteCourse;
    }
}
