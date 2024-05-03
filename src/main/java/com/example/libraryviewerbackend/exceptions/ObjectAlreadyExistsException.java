package com.example.libraryviewerbackend.exceptions;

import lombok.Getter;

@Getter
public class ObjectAlreadyExistsException extends RuntimeException {
    private final Long id;

    public ObjectAlreadyExistsException(String message, Long id) {
        super(message);
        this.id = id;
    }
}
