package edu.binghamton.srs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class SrsResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(UncategorizedSQLException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(UncategorizedSQLException ex, WebRequest request) {
        //creating exception response structure
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getSQLException().getMessage(), request.getDescription(false));
        //returning exception structure and specific status
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
