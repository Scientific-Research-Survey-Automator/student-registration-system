CREATE OR REPLACE PROCEDURE add_course_credit(course_no IN number)
IS
    credits_val NUMBER(1) := 0;
BEGIN
    IF course_no BETWEEN 100 AND 499 THEN
        credits_val := 4;
    ELSIF course_no BETWEEN 500 AND 799 THEN
        credits_val := 3;
    END IF;
    INSERT INTO COURSE_CREDIT VALUES (course_no, credits_val);
END;^
