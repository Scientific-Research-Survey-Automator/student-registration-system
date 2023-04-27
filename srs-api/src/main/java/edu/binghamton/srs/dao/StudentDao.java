package edu.binghamton.srs.dao;

import edu.binghamton.srs.http.StudentUpdateRequest;
import edu.binghamton.srs.model.Student;
import edu.binghamton.srs.util.Constants;
import edu.binghamton.srs.util.ResultSetMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.*;

import static edu.binghamton.srs.util.Constants.DB_COL_B_NUMBER;
import static edu.binghamton.srs.util.SqlUtils.*;

@Repository
public class StudentDao {
    private static final Map<String, String> DB_COLUMN_MAPPINGS = new HashMap<>();
    static {
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_B_NUMBER, "bNumber");
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_FIRST_NAME, "firstName");
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_LAST_NAME, "lastName");
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_ST_LEVEL, "stLevel");
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_GPA, "gpa");
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_EMAIL, "email");
        DB_COLUMN_MAPPINGS.put(Constants.DB_COL_BDATE, "bdate");
    }

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall fetchStudentsSp;
    private final SimpleJdbcCall deleteStudentSp;

    public StudentDao(
            NamedParameterJdbcTemplate jdbcTemplate,
            @Qualifier("fetchStudentsSp") SimpleJdbcCall fetchStudentsSp,
            @Qualifier("deleteStudentSp") SimpleJdbcCall deleteStudentSp
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.fetchStudentsSp = fetchStudentsSp;
        this.deleteStudentSp = deleteStudentSp;
    }

    public Collection<Student> findAllStudents() {
        return fetchStudentsSp.executeObject(ArrayList.class);
    }

    public Optional<Student> findByBNumber(String bNumber) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_B_NUMBER), bNumber);
        try {
            Student student = jdbcTemplate.queryForObject(FETCH_STUDENT_BY_BNO, namedParameters, ResultSetMapper::toStudent);
            return Optional.ofNullable(student);
        } catch (IncorrectResultSizeDataAccessException ir) {
            return Optional.empty();
        }
    }

    public boolean save(Student student) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_B_NUMBER), student.getBNumber())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_FIRST_NAME), student.getFirstName())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_LAST_NAME), student.getLastName())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_ST_LEVEL), student.getStLevel())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_GPA), student.getGpa())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_EMAIL), student.getEmail())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_BDATE), student.getBirthDate());
        return jdbcTemplate.update(INSERT_STUDENT, params) == 1;
    }

    public int update(String bNumber, StudentUpdateRequest student) {
        MapSqlParameterSource sqlParams = new MapSqlParameterSource()
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_FIRST_NAME), student.getFirstName())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_LAST_NAME), student.getLastName())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_GPA), student.getGpa())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_ST_LEVEL), student.getStLevel())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_BDATE), student.getBirthDate())
                .addValue(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_B_NUMBER), bNumber);
        return jdbcTemplate.update(UPDATE_STUDENT, sqlParams);
    }

    public void delete(String bNumber) {
        deleteStudentSp.execute(new MapSqlParameterSource(DB_COL_B_NUMBER, bNumber));
    }
}
