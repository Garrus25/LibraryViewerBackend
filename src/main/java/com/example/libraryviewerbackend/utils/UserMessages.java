package com.example.libraryviewerbackend.utils;

public final class UserMessages {
    public static final String USER_WITH_SPECIFIED_ID_ALREADY_EXISTS_MESSAGE = "User with id: %d already exists";
    public static final String ID_MISMATCH_IN_PATH_AND_BODY_MESSAGE = "Path id: %d does not match body id: %d";
    public static final String OBJECT_NOT_FOUND_ERROR_MESSAGE = "Object of type: %s with id: %d not found";

    public static final String INVALID_FORMAT_OF_EMAIL_ADDRESS = "Invalid format of email address";
    public static final String INVALID_ISBN = "Invalid format of ISBN";
    public static final String BOOK_WITH_ISBN_ALREADY_EXISTS = "Book with isbn: %s already exists";
    public static final String BOOK_WITH_SPEFICIED_ISBN_NOT_FOUND = "Book with isbn: %d not found";
    public static final String COULD_NOT_FIND_SPECIFIED_FILE = "Could not find specified file on the server";
    public static final String INTERNAL_SERVER_ERROR_WHEN_ACCESSING_COVER = "Internal server error when trying to access the file.";
    public static final String AUTHOR_WITH_SPECIFIED_ID_NOT_FOUND = "Author with specified id not found";

    private UserMessages() {
    }
}
