package edu.binghamton.srs.controller;

import edu.binghamton.srs.model.Enrollment;
import edu.binghamton.srs.model.EnrollmentDetails;
import edu.binghamton.srs.model.StudentDetails;
import edu.binghamton.srs.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping("/search")
    public Collection<EnrollmentDetails> getEnrollmentDetails(@RequestParam String condition) {
        return enrollmentService.searchEnrollmentDetails(condition);
    }

    @GetMapping
    public ResponseEntity<Collection<Enrollment>> getEnrollments() {
        Collection<Enrollment> enrollments = enrollmentService.fetchEnrollments();
        HttpStatus status = CollectionUtils.isEmpty(enrollments) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(enrollments);
    }

//    Using stored proc defined under PL/SQL requirements 3 for SRS
    @GetMapping("/class/{classId}/students")
    public ResponseEntity<Collection<StudentDetails>> getStudentsInAClass(@PathVariable String classId) {
        Collection<StudentDetails> students = enrollmentService.fetchStudentDetailsInAClass(classId);
        HttpStatus status = CollectionUtils.isEmpty(students) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(students);
    }

//    Using stored proc defined under PL/SQL requirements 5 for SRS
    @PostMapping("/{bNumber}/{classId}")
    public void enrollStudent(@PathVariable String bNumber, @PathVariable String classId) {
        enrollmentService.enrollStudent(bNumber, classId);
    }

//    Using stored proc defined under PL/SQL requirements 6 for SRS
    @DeleteMapping("/{bNumber}/{classId}")
    public void disenrollStudent(@PathVariable String bNumber, @PathVariable String classId) {
        enrollmentService.disenrollStudent(bNumber, classId);
    }
}
