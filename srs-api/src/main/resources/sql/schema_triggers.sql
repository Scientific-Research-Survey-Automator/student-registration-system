CREATE OR REPLACE TRIGGER add_course_credit_trigger
    AFTER INSERT
    ON COURSES
    FOR EACH ROW
DECLARE
    credit_exists NUMBER := 0;
BEGIN
    SELECT COUNT(*)
    INTO credit_exists
    FROM course_credit
    WHERE COURSE_CREDIT.COURSE# = :NEW.COURSE#;

    IF (credit_exists = 0) THEN
        add_course_credit(:NEW.COURSE#);
    END IF;
END;^
