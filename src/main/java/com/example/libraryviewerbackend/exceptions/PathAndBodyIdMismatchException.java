package com.example.libraryviewerbackend.exceptions;

public class PathAndBodyIdMismatchException extends RuntimeException{
    public PathAndBodyIdMismatchException(String message) {
        super(message);
    }
}
