package edu.binghamton.srs.service;

import edu.binghamton.srs.dao.CourseDao;
import edu.binghamton.srs.model.Course;
import edu.binghamton.srs.model.CourseCredit;
import edu.binghamton.srs.model.PrerequisiteCourse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseDao courseDao;

    public Collection<Course> findAllCourses() {
        return courseDao.findAllCourses();
    }

    public Optional<Course> findCourse(String deptCode, int courseNo) {
        return courseDao.findCourse(deptCode, courseNo);
    }

    public void saveCourse(Course course) {
        boolean saved = courseDao.saveCourse(course);
        if (!saved) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Failed to save course: %s", course));
        }
    }

    public Collection<CourseCredit> fetchCourseCredits() {
        return courseDao.fetchCourseCredits();
    }

    public Collection<PrerequisiteCourse> fetchAllPrerequisiteCourses() {
        return courseDao.fetchAllPrerequisiteCourses();
    }

    public Collection<String> fetchPrerequisiteCourses(String deptCode, int courseNo) {
        return courseDao.fetchPrerequisiteCourses(deptCode, courseNo);
    }

    public void createPrerequisiteCourse(PrerequisiteCourse prerequisiteCourse) {
        if (!courseDao.createPrerequisiteCourse(prerequisiteCourse)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Failed to add prerequisiteCourse: %s", prerequisiteCourse));
        }
    }


}
