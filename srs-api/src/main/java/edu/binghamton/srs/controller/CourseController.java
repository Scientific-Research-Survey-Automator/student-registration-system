package edu.binghamton.srs.controller;

import edu.binghamton.srs.model.Course;
import edu.binghamton.srs.model.PrerequisiteCourse;
import edu.binghamton.srs.service.CourseService;
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
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<Collection<Course>> getAllCourses() {
        Collection<Course> courses = courseService.findAllCourses();
        HttpStatus status = CollectionUtils.isEmpty(courses) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(courses);
    }

    @GetMapping("/{deptCode}/{courseNo}")
    public ResponseEntity<Course> getCourse(@PathVariable String deptCode, @PathVariable int courseNo) {
        return ResponseEntity.of(courseService.findCourse(deptCode, courseNo));
    }


    @PostMapping("/{deptCode}/{courseNo}")
    public ResponseEntity<Course> saveCourse(@PathVariable String deptCode, @PathVariable int courseNo, @RequestBody String title) {
        Course course = Course.builder()
                .deptCode(deptCode)
                .courseNo(courseNo)
                .title(title)
                .build();
        courseService.saveCourse(course);
        URI courseUri = MvcUriComponentsBuilder.fromMethodCall(
                        on(CourseController.class)
                                .getCourse(deptCode, courseNo)
                )
                .build()
                .toUri();
        return ResponseEntity.created(courseUri).body(course);
    }

    @GetMapping("/prerequisites")
    public Object getPrerequisiteCourses() {
        Collection<PrerequisiteCourse> prerequisiteCourses = courseService.fetchAllPrerequisiteCourses();
        HttpStatus status = CollectionUtils.isEmpty(prerequisiteCourses) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(prerequisiteCourses);
    }

//    Using stored proc defined under PL/SQL requirements 4 for SRS
    @GetMapping("/prerequisites/{deptCode}/{courseNo}")
    public Object getPrerequisiteCourses(@PathVariable String deptCode, @PathVariable int courseNo) {
        Collection<String> prerequisiteCourses = courseService.fetchPrerequisiteCourses(deptCode, courseNo);
        HttpStatus status = CollectionUtils.isEmpty(prerequisiteCourses) ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(prerequisiteCourses);
    }

    @PostMapping("/prerequisites")
    public ResponseEntity<PrerequisiteCourse> createPrerequisiteCourse(@RequestBody PrerequisiteCourse prerequisiteCourse) {
        courseService.createPrerequisiteCourse(prerequisiteCourse);
        URI courseUri = MvcUriComponentsBuilder.fromMethodCall(
                        on(CourseController.class)
                                .getPrerequisiteCourses(prerequisiteCourse.getDeptCode(), prerequisiteCourse.getCourseNo())
                )
                .build()
                .toUri();
        return ResponseEntity.created(courseUri).body(prerequisiteCourse);
    }

}
