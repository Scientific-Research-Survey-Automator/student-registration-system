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

--     question 5 & 6 to update class size
CREATE OR REPLACE TRIGGER enrollment_update_trigger
    AFTER INSERT OR DELETE
    ON G_ENROLLMENTS
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
        UPDATE CLASSES c
        SET c.CLASS_SIZE = c.CLASS_SIZE + 1
        WHERE c.CLASSID = :NEW.CLASSID;
    ELSIF DELETING THEN
        UPDATE CLASSES c
        SET c.CLASS_SIZE = c.CLASS_SIZE - 1
        WHERE c.CLASSID = :OLD.CLASSID;
    END IF;
END;^


--     question 7 student delete trigger

CREATE OR REPLACE TRIGGER student_delete_trigger
    AFTER DELETE
    ON STUDENTS
    FOR EACH ROW
BEGIN
    DELETE FROM G_ENROLLMENTS
    WHERE "G_B#" = :OLD."B#";
END;^

/*
    8.(8 points) Write triggers to add tuples to the Logs table automatically whenever a student is deleted
    from the Students table, or when a student is successfully enrolled into or dropped from a class (i.e.,
    when a tuple is inserted into or deleted from the G_Enrollments table). For a logs record for
    G_Enrollments, the key value is the concatenation of the B# value, a comma, and the classid value.
*/

CREATE OR REPLACE TRIGGER student_log_trigger
    AFTER DELETE
    ON STUDENTS
    FOR EACH ROW
BEGIN
    INSERT INTO LOGS
    VALUES (logseq.NEXTVAL, (SELECT USER FROM dual), CURRENT_TIMESTAMP, 'STUDENTS', 'DELETE', :OLD.B#);
END;^


CREATE OR REPLACE TRIGGER enrollment_log_trigger
    AFTER INSERT OR DELETE
    ON G_ENROLLMENTS
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO LOGS
        VALUES (logseq.NEXTVAL, (SELECT USER FROM dual), CURRENT_TIMESTAMP, 'G_ENROLLMENTS', 'INSERT',
                TO_CHAR(:NEW.G_B#) || ',' || TO_CHAR(:NEW.CLASSID));
    ELSIF DELETING THEN
        INSERT INTO LOGS
        VALUES (logseq.NEXTVAL, (SELECT USER FROM dual), CURRENT_TIMESTAMP, 'G_ENROLLMENTS', 'DELETE',
                TO_CHAR(:OLD.G_B#) || ',' || TO_CHAR(:OLD.CLASSID));
    END IF;
END;^


ALTER TRIGGER enrollment_update_trigger DISABLE^
