package edu.binghamton.srs.util;

public class SqlUtils {

    private SqlUtils() {
        throw new IllegalStateException("Utility class");
    }



    //    Students
    public static final String FETCH_STUDENTS_SP = "show_students";
    public static final String DELETE_STUDENT_SP = "del_student";
    public static final String FETCH_STUDENT_BY_BNO = "SELECT * FROM students WHERE b# = :bNumber";
    public static final String INSERT_STUDENT = "INSERT INTO students VALUES (:bNumber, :firstName, :lastName, :stLevel, :gpa, :email, :bdate)";
    public static final String UPDATE_STUDENT = "UPDATE students SET first_name = :firstName, last_name = :lastName, gpa = :gpa, st_level = :stLevel, bdate = :bdate WHERE b# = :bNumber";

//    Courses
    public static final String FETCH_ALL_COURSES_SP = "show_courses";
    public static final String FETCH_ALL_COURSE_CREDITS_SP = "show_course_credit";
    public static final String FETCH_ALL_PREREQUISITES_SP = "show_prerequisites";
    public static final String FETCH_PREREQUISITES_SP = "get_prerequisite_courses";

    public static final String FETCH_COURSE = "SELECT * FROM COURSES WHERE courses.course# = :courseNo AND dept_code = :deptCode";
    public static final String INSERT_COURSE = "INSERT INTO COURSES VALUES (:deptCode, :courseNo, :title)";

    public static final String INSERT_PREREQUISITE_COURSE = "INSERT INTO prerequisites VALUES (:deptCode, :courseNo, :preDeptCode, :preCourseNo)";


//    Enrollment/Classes
    public static final String FETCH_ENROLLMENT_SP = "show_g_enrollments";
    public static final String FETCH_STUDENTS_ENROLLED_IN_CLASS_SP = "list_class";
    public static final String ENROLL_STUDENT_IN_CLASS_SP = "enroll_grad";
    public static final String DISENROLL_STUDENT_IN_CLASS_SP = "drop_grad";
    public static final String FETCH_CLASSES_SP = "show_classes";
    public static final String FETCH_SCORE_GRADES = "show_score_grade";

    public static final String FETCH_ENROLLMENT_DETAILS = "SELECT * FROM DISPLAY_REGISTRATION_INFO";
    public static final String WHERE_CLAUSE = " WHERE :clause";

    //    Logs
    public static final String FETCH_LOGS = "show_logs";

}
