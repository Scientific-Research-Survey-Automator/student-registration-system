package edu.binghamton.srs.controller;

import edu.binghamton.srs.model.EnrollmentDetails;
import edu.binghamton.srs.model.Student;
import edu.binghamton.srs.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/class/{classId}/students")
    public Collection<Student> getStudentsInAClass(@PathVariable String classId) {
        return enrollmentService.fetchStudentsInAClass(classId);
    }

    // TODO: 4/21/23 Using stored proc 5 enroll a student in a class
    @PostMapping("/{bNumber}/{classId}")
    public void enrollStudent(@PathVariable String bNumber, @PathVariable String classId) {
    }

    // TODO: 4/21/23 Using stored proc 6 disenroll student from a class
    @DeleteMapping("/{bNumber}/{classId}")
    public void disenrollStudent(@PathVariable String bNumber, @PathVariable String classId) {
    }
}
