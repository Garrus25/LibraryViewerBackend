package com.example.libraryviewerbackend.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class DatabaseCredentialsProvider {
    @Getter
    private static String url;
    @Getter
    private static String login;
    @Getter
    private static String password;

    private static void initializeDataFromEnvironmentVariables() {
        url = System.getenv("DATABASE_SERVER_URL");
        login = System.getenv("DATABASE_LOGIN");
        password = System.getenv("DATABASE_PASSWORD");
    }

    static {
        initializeDataFromEnvironmentVariables();
    }

    private DatabaseCredentialsProvider(){}

}
