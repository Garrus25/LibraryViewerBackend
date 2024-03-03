package com.example.libraryviewerbackend.exceptions;

import lombok.Getter;

@Getter
public class ObjectAlreadyExistsException extends RuntimeException {
    private final Integer id;
    public ObjectAlreadyExistsException(String message, Integer id) {
        super(message);
        this.id = id;
    }
}
