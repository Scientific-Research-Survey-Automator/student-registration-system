package edu.binghamton.srs.dao;

import edu.binghamton.srs.model.Course;
import edu.binghamton.srs.model.PrerequisiteCourse;
import edu.binghamton.srs.util.Constants;
import edu.binghamton.srs.util.ResultSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class CourseDao {

    private static final Map<String, String> DB_COLUMN_MAPPINGS = new HashMap<>();
    static {
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_DEPT_CODE, "deptCode");
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_COURSE_NO, "courseNo");
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_TITLE, "title");
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_PRE_DEPT_CODE, "preDeptCode");
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_PRE_COURSE_NO, "preCourseNo");
    }

    private static final String FETCH_COURSE = "SELECT * FROM COURSES INNER JOIN COURSE_CREDIT CC on COURSES.COURSE# = CC.COURSE# WHERE courses.course# = :courseNo AND dept_code = :deptCode";
    private static final String FETCH_ALL_COURSES = "SELECT * FROM COURSES LEFT JOIN COURSE_CREDIT CC on COURSES.COURSE# = CC.COURSE#";
    private static final String INSERT_COURSE = "INSERT INTO COURSES VALUES (:deptCode, :courseNo, :title)";

    private static final String FETCH_ALL_PREREQUISITE_COURSES = "SELECT * FROM prerequisites";
    private static final String FETCH_PREREQUISITE_COURSES = "SELECT * FROM prerequisites WHERE course# = :courseNo AND dept_code = :deptCode";
    private static final String INSERT_PREREQUISITE_COURSE = "INSERT INTO prerequisites VALUES (:deptCode, :courseNo, :preDeptCode, :preCourseNo)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    // ,TODO: 4/21/23 Using stored proc 2 fetch all courses
        public Collection<Course> findAllCourses() {
            return jdbcTemplate.query(FETCH_ALL_COURSES, ResultSetMapper::toCourse);
        }

        public Optional<Course> findCourse(String deptCode, int courseNo) {
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_DEPT_CODE), deptCode)
                    .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_COURSE_NO), courseNo);
        try {
            Course course = jdbcTemplate.queryForObject(FETCH_COURSE, sqlParameterSource, ResultSetMapper::toCourse);
            return Optional.ofNullable(course);
        } catch (IncorrectResultSizeDataAccessException ir) {
            return Optional.empty();
        }
    }

    public boolean saveCourse(Course course) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_DEPT_CODE), course.getDeptCode())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_COURSE_NO), course.getCourseNo())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_TITLE), course.getTitle());
        return jdbcTemplate.update(INSERT_COURSE, sqlParameterSource) == 1;
    }

    // TODO: 4/21/23 Using stored proc 4 fetch prerequisite courses, add boolean to support direct/indirect prerequisites
    public List<PrerequisiteCourse> fetchAllPrerequisiteCourses() {
        return jdbcTemplate.query(FETCH_ALL_PREREQUISITE_COURSES, ResultSetMapper::toPrerequisiteCourse);
    }

    public List<PrerequisiteCourse> fetchPrerequisiteCourses(String deptCode, int courseNo) {
        SqlParameterSource sqlParams = new MapSqlParameterSource()
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_DEPT_CODE), deptCode)
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_COURSE_NO), courseNo);
        return jdbcTemplate.query(FETCH_PREREQUISITE_COURSES, sqlParams, ResultSetMapper::toPrerequisiteCourse);
    }

    public boolean createPrerequisiteCourse(PrerequisiteCourse prerequisiteCourse) {
        SqlParameterSource sqlParams = new MapSqlParameterSource()
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_DEPT_CODE), prerequisiteCourse.getDeptCode())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_COURSE_NO), prerequisiteCourse.getCourseNo())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_PRE_DEPT_CODE), prerequisiteCourse.getPreDeptCode())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_PRE_COURSE_NO), prerequisiteCourse.getPreCourseNo());
        return jdbcTemplate.update(INSERT_PREREQUISITE_COURSE, sqlParams) == 1;
    }


}
