package edu.binghamton.srs.dao;

import edu.binghamton.srs.model.Course;
import edu.binghamton.srs.model.CourseCredit;
import edu.binghamton.srs.model.PrerequisiteCourse;
import edu.binghamton.srs.util.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.*;

import static edu.binghamton.srs.util.Constants.*;
import static edu.binghamton.srs.util.SqlUtils.*;

@Repository
public class CourseDao {

    private static final Map<String, String> DB_COLUMN_MAPPINGS = new HashMap<>();
    static {
        DB_COLUMN_MAPPINGS.put(DB_COL_DEPT_CODE, "deptCode");
        DB_COLUMN_MAPPINGS.put(DB_COL_COURSE_NO, "courseNo");
        DB_COLUMN_MAPPINGS.put(DB_COL_TITLE, "title");
        DB_COLUMN_MAPPINGS.put(DB_COL_PRE_DEPT_CODE, "preDeptCode");
        DB_COLUMN_MAPPINGS.put(DB_COL_PRE_COURSE_NO, "preCourseNo");
    }

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcCall fetchAllCoursesSp;
    private final SimpleJdbcCall fetchAllCourseCreditsSp;
    private final SimpleJdbcCall fetchAllPrerequisiteCoursesSp;
    private final SimpleJdbcCall fetchPrerequisiteCoursesForCourseSp;

    @Autowired
    public CourseDao(
            NamedParameterJdbcTemplate jdbcTemplate,
                     @Qualifier("fetchAllCoursesSp") SimpleJdbcCall fetchAllCoursesSp,
                     @Qualifier("fetchAllCourseCreditsSp") SimpleJdbcCall fetchAllCourseCreditsSp,
                     @Qualifier("fetchAllPrerequisiteCoursesSp") SimpleJdbcCall fetchAllPrerequisiteCoursesSp,
                     @Qualifier("fetchPrerequisiteCoursesForCourseSp") SimpleJdbcCall fetchPrerequisiteCoursesForCourseSp
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.fetchAllCoursesSp = fetchAllCoursesSp;
        this.fetchAllCourseCreditsSp = fetchAllCourseCreditsSp;
        this.fetchAllPrerequisiteCoursesSp = fetchAllPrerequisiteCoursesSp;
        this.fetchPrerequisiteCoursesForCourseSp = fetchPrerequisiteCoursesForCourseSp;
    }

    /**
     * Fetches all courses by executing stored procedure
     * @return a list of {@link Course}
     */
    public Collection<Course> findAllCourses() {
        return fetchAllCoursesSp.executeObject(ArrayList.class);
    }

    public Optional<Course> findCourse(String deptCode, int courseNo) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(DB_COLUMN_MAPPINGS.get(DB_COL_DEPT_CODE), deptCode)
                .addValue(DB_COLUMN_MAPPINGS.get(DB_COL_COURSE_NO), courseNo);
        try {
            Course course = jdbcTemplate.queryForObject(FETCH_COURSE, sqlParameterSource, ResultSetMapper::toCourse);
            return Optional.ofNullable(course);
        } catch (IncorrectResultSizeDataAccessException ir) {
            return Optional.empty();
        }
    }

    public boolean saveCourse(Course course) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(DB_COLUMN_MAPPINGS.get(DB_COL_DEPT_CODE), course.getDeptCode())
                .addValue(DB_COLUMN_MAPPINGS.get(DB_COL_COURSE_NO), course.getCourseNo())
                .addValue(DB_COLUMN_MAPPINGS.get(DB_COL_TITLE), course.getTitle());
        return jdbcTemplate.update(INSERT_COURSE, sqlParameterSource) == 1;
    }

    public List<PrerequisiteCourse> fetchAllPrerequisiteCourses() {
        return fetchAllPrerequisiteCoursesSp.executeObject(ArrayList.class);
    }

    public Collection<String> fetchPrerequisiteCourses(String deptCode, int courseNo) {
        SqlParameterSource sqlParams = new MapSqlParameterSource()
                .addValue(DB_COL_COURSE_NO, courseNo)
                .addValue(DB_COL_DEPT_CODE, deptCode);
        return fetchPrerequisiteCoursesForCourseSp.executeObject(ArrayList.class, sqlParams);
    }

    public Collection<CourseCredit> fetchCourseCredits() {
        return fetchAllCourseCreditsSp.executeObject(ArrayList.class);
    }
    public boolean createPrerequisiteCourse(PrerequisiteCourse prerequisiteCourse) {
        SqlParameterSource sqlParams = new MapSqlParameterSource()
                .addValue(DB_COLUMN_MAPPINGS.get(DB_COL_DEPT_CODE), prerequisiteCourse.getDeptCode())
                .addValue(DB_COLUMN_MAPPINGS.get(DB_COL_COURSE_NO), prerequisiteCourse.getCourseNo())
                .addValue(DB_COLUMN_MAPPINGS.get(DB_COL_PRE_DEPT_CODE), prerequisiteCourse.getPreDeptCode())
                .addValue(DB_COLUMN_MAPPINGS.get(DB_COL_PRE_COURSE_NO), prerequisiteCourse.getPreCourseNo());
        return jdbcTemplate.update(INSERT_PREREQUISITE_COURSE, sqlParams) == 1;
    }


}
