package edu.binghamton.srs.dao;

import edu.binghamton.srs.http.StudentUpdateRequest;
import edu.binghamton.srs.model.Student;
import edu.binghamton.srs.util.Constants;
import edu.binghamton.srs.util.ResultSetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
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
    
    // TODO: 4/20/23 Using stored proc 2 fetch all students
    private static final String FETCH_STUDENTS = "SELECT * FROM students";
    private static final String FETCH_STUDENT_BY_BNO = "SELECT * FROM students WHERE b# = :bNumber";
    private static final String INSERT_STUDENT = "INSERT INTO students VALUES (:bNumber, :firstName, :lastName, :stLevel, :gpa, :email, :bdate)";
    private static final String UPDATE_STUDENT = "UPDATE students SET first_name = :firstName, last_name = :lastName, gpa = :gpa, st_level = :stLevel, bdate = :bdate WHERE b# = :bNumber";

    // TODO: 4/20/23 Using stored proc 7 delete a student
    private static final String DELETE_STUDENT = "DELETE FROM students WHERE b# = :bNumber";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Collection<Student> findAllStudents() {
        return jdbcTemplate.query(FETCH_STUDENTS, ResultSetMapper::toStudent);
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

    public boolean delete(String bNumber) {
        MapSqlParameterSource sqlParams = new MapSqlParameterSource(DB_COLUMN_MAPPINGS.get(Constants.DB_COL_B_NUMBER), bNumber);
        return jdbcTemplate.update(DELETE_STUDENT, sqlParams) == 1;
    }
}
