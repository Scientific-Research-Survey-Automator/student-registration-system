package edu.binghamton.srs.service;

import edu.binghamton.srs.dao.EnrollmentDao;
import edu.binghamton.srs.model.EnrollmentDetails;
import edu.binghamton.srs.model.Student;
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

    public Collection<Student> fetchStudentsInAClass(String classId) {
        return enrollmentDao.fetchStudentsInAClass(classId);
    }

}
