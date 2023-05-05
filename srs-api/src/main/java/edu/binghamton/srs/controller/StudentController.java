package edu.binghamton.srs.controller;

import edu.binghamton.srs.http.StudentUpdateRequest;
import edu.binghamton.srs.model.Student;
import edu.binghamton.srs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

//    Fetch all students
    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents() {
        Collection<Student> students = studentService.findAllStudents();
        HttpStatus status = CollectionUtils.isEmpty(students) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(students);
    }

//    Fetch a particular student
    @GetMapping("/{bNumber}")
    public ResponseEntity<Student> getStudent(@PathVariable String bNumber) {
        return ResponseEntity.of(studentService.findStudentByBNumber(bNumber));
    }

//    Create a student record
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        URI studentUri = MvcUriComponentsBuilder.fromMethodCall(
                on(StudentController.class)
                        .getStudent(student.getBNumber())
                )
                .build()
                .toUri();
        return ResponseEntity.created(studentUri).body(student);
    }

//    Update student details
    @PutMapping("/{bNumber}")
    public ResponseEntity<Boolean> updateStudentDetails(@PathVariable String bNumber, @RequestBody StudentUpdateRequest updateRequest) {
        boolean status = studentService.updateStudent(bNumber, updateRequest);
        HttpStatus httpStatus = status ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        return ResponseEntity.status(httpStatus).body(status);
    }

//    Using stored proc defined under PL/SQL requirements 7 for SRS
//    Delete a student from the registration system
    @DeleteMapping("/{bNumber}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable String bNumber) {
        studentService.deleteStudent(bNumber);
        return ResponseEntity.noContent().build();
    }
}
