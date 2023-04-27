package edu.binghamton.srs.controller;

import edu.binghamton.srs.model.Table;
import edu.binghamton.srs.service.CourseService;
import edu.binghamton.srs.service.EnrollmentService;
import edu.binghamton.srs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tables")
public class TablesController {

    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;

//    Using stored proc defined under PL/SQL requirements 2 for SRS
    @GetMapping("/{table}")
    public ResponseEntity<Collection> getTableData(@PathVariable Table table) {

        Collection<? extends Object> results;
        switch (table) {
            case STUDENTS:
                results = studentService.findAllStudents();
                break;
            case COURSES:
                results = courseService.findAllCourses();
                break;
            case PREREQUISITES:
                results = courseService.fetchAllPrerequisiteCourses();
                break;
            case COURSE_CREDIT:
                results = courseService.fetchCourseCredits();
                break;
            case CLASSES:
                results = enrollmentService.fetchClasses();
                break;
            case G_ENROLLMENTS:
                results = enrollmentService.fetchEnrollments();
                break;
            case SCORE_GRADE:
                results = enrollmentService.fetchScoreGrades();
                break;
            case LOGS:
                results = enrollmentService.fetchLogs();
                break;
            default:
                results = null;
        }
        HttpStatus status = CollectionUtils.isEmpty(results) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(results);
    }

    @GetMapping("/list")
    public ResponseEntity<Table[]> getTables() {
        return ResponseEntity.ok(Table.values());
    }
}
