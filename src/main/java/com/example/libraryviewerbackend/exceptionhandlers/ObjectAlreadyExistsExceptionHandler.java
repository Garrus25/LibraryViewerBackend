package com.example.libraryviewerbackend.exceptionhandlers;

import com.example.libraryviewerbackend.exceptions.ObjectAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ObjectAlreadyExistsExceptionHandler {
    @ExceptionHandler({ ObjectAlreadyExistsException.class })
    public ResponseEntity<Object> handleException(ObjectAlreadyExistsException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(HttpStatus.CONFLICT,
                        List.of(String.format(ex.getMessage(), ex.getId())),
                        LocalDateTime.now()),
                new HttpHeaders(),
                HttpStatus.CONFLICT);
    }
}
