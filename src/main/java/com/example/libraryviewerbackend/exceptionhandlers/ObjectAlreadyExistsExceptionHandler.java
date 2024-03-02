package com.example.libraryviewerbackend.exceptionhandlers;

import com.example.libraryviewerbackend.exceptions.ObjectAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ObjectAlreadyExistsExceptionHandler {
    @ExceptionHandler({ ObjectAlreadyExistsException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new ResponseEntity<>(
                errors, new HttpHeaders(), HttpStatus.CONFLICT);
    }
}
