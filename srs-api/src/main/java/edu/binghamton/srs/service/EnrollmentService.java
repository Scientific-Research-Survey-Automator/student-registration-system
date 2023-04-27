package edu.binghamton.srs.service;

import edu.binghamton.srs.dao.EnrollmentDao;
import edu.binghamton.srs.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentDao enrollmentDao;

    public Collection<EnrollmentDetails> searchEnrollmentDetails(String condition) {
        return enrollmentDao.search(condition);
    }

    public Collection<StudentDetails> fetchStudentDetailsInAClass(String classId) {
        return enrollmentDao.fetchStudentDetailsInAClass(classId);
    }

    public void enrollStudent(String bNumber, String classId) {
        try {
            enrollmentDao.enrollStudent(bNumber, classId);
        } catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Failed to enroll student(B#: %s) in class (%s) with error message: %s", bNumber, classId, e.getMessage()));
        }
    }

    public void disenrollStudent(String bNumber, String classId) {
        try {
            enrollmentDao.disenrollStudent(bNumber, classId);
        } catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Failed to disenroll student(B#: %s) in class (%s) with error message: %s", bNumber, classId, e.getMessage()));
        }
    }

    public Collection<Enrollment> fetchEnrollments() {
        return enrollmentDao.fetchEnrollments();
    }

    public Collection<Classes> fetchClasses() {
        return enrollmentDao.fetchClasses();
    }

    public Collection<ScoreGrade> fetchScoreGrades() {
        return enrollmentDao.fetchScoreGrades();
    }

    public Collection<Object> fetchLogs() {
        return enrollmentDao.fetchLogs();
    }
}
