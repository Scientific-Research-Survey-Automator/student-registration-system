package edu.binghamton.srs.util;

public class Constants {


    private Constants() {
        throw new IllegalStateException("Utility class");
    }

//    DB
    public static final String DB_PACKAGE_NAME = "srs";
    public static final String DB_CURSOR_OUTPUT = "CURSOR_OUTPUT";

    //    Students
    public static final String DB_COL_B_NUMBER = "B#";
    public static final String DB_COL_FIRST_NAME = "first_name";
    public static final String DB_COL_LAST_NAME = "last_name";
    public static final String DB_COL_ST_LEVEL = "st_level";
    public static final String DB_COL_GPA = "gpa";
    public static final String DB_COL_EMAIL = "email";
    public static final String DB_COL_BDATE = "bdate";

//    Courses
    public static final String DB_COL_DEPT_CODE = "dept_code";
    public static final String DB_COL_COURSE_NO = "course#";
    public static final String DB_COL_TITLE = "title";
    public static final String DB_COL_CREDITS = "credits";
    public static final String DB_COL_PRE_DEPT_CODE = "pre_dept_code";
    public static final String DB_COL_PRE_COURSE_NO = "pre_course#";

//    Class/Enrollment
    public static final String DB_COL_G_B_NUMBER = "G_B#";
    public static final String DB_COL_CLASSID = "classid";
    public static final String DB_COL_SECTION = "sect#";
    public static final String DB_COL_YEAR = "year";
    public static final String DB_COL_SEMESTER = "semester";
    public static final String DB_COL_LIMIT = "limit";
    public static final String DB_COL_CLASS_SIZE = "class_size";
    public static final String DB_COL_ROOM = "room";
    public static final String DB_COL_SCORE = "score";
    public static final String DB_COL_GRADE = "lgrade";

}
