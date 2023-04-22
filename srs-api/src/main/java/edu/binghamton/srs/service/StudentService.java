package edu.binghamton.srs.service;

import edu.binghamton.srs.dao.StudentDao;
import edu.binghamton.srs.http.StudentUpdateRequest;
import edu.binghamton.srs.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDao studentDao;

    public Collection<Student> findAllStudents() {
        return studentDao.findAllStudents();
    }

    public Optional<Student> findStudentByBNumber(String bNumber) {
        return studentDao.findByBNumber(bNumber);
    }

    public void saveStudent(Student student) {
        if (!studentDao.save(student)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Failed to save student: %s", student));
        }
    }

    public boolean updateStudent(String bNumber, StudentUpdateRequest updateRequest) {
        return studentDao.update(bNumber, updateRequest) == 1;
    }

    public void deleteStudent(String bNumber) {
        if (!studentDao.delete(bNumber)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Failed to delete student with B#: %s", bNumber));
        }
    }
}
