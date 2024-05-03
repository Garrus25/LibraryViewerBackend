package com.example.libraryviewerbackend.exceptions;

import lombok.Getter;

@Getter
public class ObjectNotFoundException extends RuntimeException {
    private final Long objectId;
    private final String objectType;

    public ObjectNotFoundException(String message, Long objectId, Class<?> objectClass) {
        super(message);
        this.objectId = objectId;
        this.objectType = objectClass.getSimpleName();
    }
}
