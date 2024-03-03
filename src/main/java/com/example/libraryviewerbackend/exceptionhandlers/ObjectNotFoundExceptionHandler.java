package com.example.libraryviewerbackend.exceptionhandlers;

import com.example.libraryviewerbackend.exceptions.ObjectNotFoundException;
import com.example.libraryviewerbackend.utils.UserMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ObjectNotFoundExceptionHandler {
    @ExceptionHandler({ ObjectNotFoundException.class })
    public ResponseEntity<Object> handleException(ObjectNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(HttpStatus.NOT_FOUND,
                        List.of(String.format(UserMessages.OBJECT_NOT_FOUND_ERROR_MESSAGE, ex.getObjectType(), ex.getObjectId())),
                        LocalDateTime.now()),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }
}
