package com.example.libraryviewerbackend.exceptionhandlers;

import com.example.libraryviewerbackend.exceptions.PathAndBodyIdMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class PathAndBodyIdMismatchExceptionHandler {
    @ExceptionHandler({PathAndBodyIdMismatchException.class})
    public ResponseEntity<Object> handleException(PathAndBodyIdMismatchException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(HttpStatus.BAD_REQUEST,
                        List.of(String.format(ex.getMessage(), ex.getPathId(), ex.getBodyId())),
                        LocalDateTime.now()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }
}
