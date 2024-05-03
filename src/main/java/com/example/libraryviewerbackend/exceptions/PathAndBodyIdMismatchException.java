package com.example.libraryviewerbackend.exceptions;

import lombok.Getter;

@Getter
public class PathAndBodyIdMismatchException extends RuntimeException {
    private final Integer pathId;
    private final Integer bodyId;

    public PathAndBodyIdMismatchException(String message, Integer pathId, Integer bodyId) {
        super(message);
        this.pathId = pathId;
        this.bodyId = bodyId;
    }
}
