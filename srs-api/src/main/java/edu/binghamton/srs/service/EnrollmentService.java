package edu.binghamton.srs.service;

import edu.binghamton.srs.dao.EnrollmentDao;
import edu.binghamton.srs.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        enrollmentDao.enrollStudent(bNumber, classId);
    }

    public void disenrollStudent(String bNumber, String classId) {
        enrollmentDao.disenrollStudent(bNumber, classId);
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
