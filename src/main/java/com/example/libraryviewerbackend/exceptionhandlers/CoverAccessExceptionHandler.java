package com.example.libraryviewerbackend.exceptionhandlers;

import com.example.libraryviewerbackend.exceptions.CoverAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class CoverAccessExceptionHandler {
    @ExceptionHandler({CoverAccessException.class})
    public ResponseEntity<Object> handleException(CoverAccessException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(HttpStatus.BAD_REQUEST,
                        List.of(ex.getMessage()),
                        LocalDateTime.now()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }
}
