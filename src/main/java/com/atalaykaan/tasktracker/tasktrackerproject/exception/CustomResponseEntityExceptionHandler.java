package com.atalaykaan.tasktracker.tasktrackerproject.exception;

import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {

        ExceptionDetails exceptionDetails =
                new ExceptionDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public final ResponseEntity<ExceptionDetails> handleTaskNotFoundException(Exception ex, WebRequest request) throws TaskNotFoundException {

        ExceptionDetails exceptionDetails =
                new ExceptionDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String errorMessage = "Total field errors: "
                + ex.getErrorCount()
                + ", Errors: "
                + ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();

        ExceptionDetails exceptionDetails =
                new ExceptionDetails(LocalDate.now(), errorMessage, request.getDescription(false));

        return new ResponseEntity<Object>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ExceptionDetails exceptionDetails =
                new ExceptionDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<Object>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
