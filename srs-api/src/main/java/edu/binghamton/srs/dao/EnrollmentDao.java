package edu.binghamton.srs.dao;

import edu.binghamton.srs.model.Classes;
import edu.binghamton.srs.model.EnrollmentDetails;
import edu.binghamton.srs.model.Student;
import edu.binghamton.srs.util.ResultSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class EnrollmentDao {

    private static final String FETCH_STUDENTS_ENROLLED_IN_CLASS = "SELECT S.* FROM G_ENROLLMENTS INNER JOIN STUDENTS S on G_ENROLLMENTS.G_B# = S.B# WHERE CLASSID = :classId";
    private static final String FETCH_ENROLLMENT_DETAILS = "SELECT * FROM DISPLAY_REGISTRATION_INFO";
    private static final String WHERE_CLAUSE = " WHERE :clause";


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public Collection<EnrollmentDetails> search(String condition) {
        StringBuilder sqlBuilder = new StringBuilder().append(FETCH_ENROLLMENT_DETAILS);
        if (StringUtils.hasLength(condition))
            sqlBuilder.append(WHERE_CLAUSE.replace(":clause", condition));
        return jdbcTemplate.query(sqlBuilder.toString(), this::mapRow);
    }

    private EnrollmentDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return EnrollmentDetails.builder()
                .student(ResultSetMapper.toStudent(rs, rowNum))
                .classes(Classes.builder()
                .classId(rs.getString("classid"))
                .section(rs.getInt("sect#"))
                .semester(rs.getString("semester"))
                .year(getYear(rs))
                .limit(rs.getInt("limit"))
                .size(rs.getInt("class_size"))
                .room(rs.getString("room"))
                        .course(ResultSetMapper.toCourse(rs, rowNum)).build())
                .score(rs.getDouble("score"))
                .grade(rs.getString("lgrade"))
                .build();
    }

    private static Year getYear(ResultSet rs) throws SQLException {
        String year = rs.getString("year");
        return year == null ? null : Year.parse(year);
    }

    // TODO: 4/21/23 Using stored proc 3 fetch students in a class
    public Collection<Student> fetchStudentsInAClass(String classId) {
        SqlParameterSource sqlParams = new MapSqlParameterSource("classId", classId);
        return namedParameterJdbcTemplate.query(FETCH_STUDENTS_ENROLLED_IN_CLASS, sqlParams, ResultSetMapper::toStudent);
    }
}
