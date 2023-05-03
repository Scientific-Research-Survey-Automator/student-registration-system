package edu.binghamton.srs.util;

import edu.binghamton.srs.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import static edu.binghamton.srs.util.Constants.*;
import static edu.binghamton.srs.util.Constants.DB_COL_GRADE;

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

    public static StudentDetails toStudentDetails(ResultSet resultSet, int rowNum) throws SQLException {
        LOGGER.trace("Mapping row no: {} to student object...", rowNum);
        StudentDetails student = StudentDetails.builder()
                .bNumber(resultSet.getString(Constants.DB_COL_B_NUMBER))
                .firstName(resultSet.getString(Constants.DB_COL_FIRST_NAME))
                .lastName(resultSet.getString(Constants.DB_COL_LAST_NAME))
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

    public static CourseCredit toCourseCredit(ResultSet resultSet, int rowNum) throws SQLException {
        LOGGER.trace("Mapping row no: {} to courseCredit object...", rowNum);
        CourseCredit courseCredit = CourseCredit.builder()
                .courseNo(resultSet.getInt(Constants.DB_COL_COURSE_NO))
                .credits(resultSet.getInt(Constants.DB_COL_CREDITS))
                .build();
        LOGGER.trace("Mapped row to courseCredit object: {}.", courseCredit);
        return courseCredit;
    }

    public static Enrollment toEnrollment(ResultSet resultSet, int rowNum) throws SQLException {
        LOGGER.trace("Mapping row no: {} to enrollment object...", rowNum);
        Enrollment enrollment = Enrollment.builder()
                .bNumber(resultSet.getString(Constants.DB_COL_G_B_NUMBER))
                .classId(resultSet.getString(Constants.DB_COL_CLASSID))
                .score(resultSet.getDouble(Constants.DB_COL_SCORE))
                .build();
        LOGGER.trace("Mapped row to enrollment object: {}.", enrollment);
        return enrollment;
    }

    public static Classes toClasses(ResultSet resultSet, int rowNum) throws SQLException {
        LOGGER.trace("Mapping row no: {} to classes object...", rowNum);
        Classes classes = Classes.builder()
                .classId(resultSet.getString(Constants.DB_COL_CLASSID))
                .deptCode(resultSet.getString(Constants.DB_COL_DEPT_CODE))
                .courseNo(resultSet.getInt(Constants.DB_COL_COURSE_NO))
                .section(resultSet.getInt(Constants.DB_COL_SECTION))
                .year(Year.of(resultSet.getInt(Constants.DB_COL_YEAR)))
                .semester(resultSet.getString(Constants.DB_COL_SEMESTER))
                .limit(resultSet.getInt(Constants.DB_COL_LIMIT))
                .size(resultSet.getInt(Constants.DB_COL_CLASS_SIZE))
                .room(resultSet.getString(Constants.DB_COL_ROOM))
                .build();
        LOGGER.trace("Mapped row to classes object: {}.", classes);
        return classes;
    }

    public static EnrollmentDetails toEnrollmentDetails(ResultSet rs, int rowNum) throws SQLException {
        LOGGER.trace("Mapping row no: {} to enrollmentDetails object...", rowNum);
        EnrollmentDetails enrollmentDetails = EnrollmentDetails.builder()
                .student(ResultSetMapper.toStudent(rs, rowNum))
                .classes(Classes.builder()
                        .classId(rs.getString(DB_COL_CLASSID))
                        .section(rs.getInt(DB_COL_SECTION))
                        .semester(rs.getString(DB_COL_SEMESTER))
                        .year(Year.of(rs.getInt(DB_COL_YEAR)))
                        .limit(rs.getInt(DB_COL_LIMIT))
                        .size(rs.getInt(DB_COL_CLASS_SIZE))
                        .room(rs.getString(DB_COL_ROOM))
                        .courseNo(rs.getInt(DB_COL_COURSE_NO))
                        .deptCode(rs.getString(DB_COL_DEPT_CODE))
                        .build()
                )
                .score(rs.getDouble(DB_COL_SCORE))
                .grade(rs.getString(DB_COL_GRADE))
                .build();
        LOGGER.trace("Mapped row to enrollmentDetails object: {}.", enrollmentDetails);
        return enrollmentDetails;
    }

    public static ScoreGrade toScoreGrade(ResultSet resultSet, int rowNum) throws SQLException {
        LOGGER.trace("Mapping row no: {} to scoreGrade object...", rowNum);
        ScoreGrade scoreGrade = new ScoreGrade(resultSet.getDouble(DB_COL_SCORE), resultSet.getString(DB_COL_GRADE));
        LOGGER.trace("Mapped row to scoreGrade object: {}.", scoreGrade);
        return scoreGrade;
    }
}
