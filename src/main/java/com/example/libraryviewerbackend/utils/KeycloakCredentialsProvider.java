package com.example.libraryviewerbackend.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KeycloakCredentialsProvider {
    @Getter
    private static String login;
    @Getter
    private static String password;
    @Getter
    private static String url;
    @Getter
    private static String clientId;
    @Getter
    private static String realm;

    private static void initializeDataFromEnvironmentVariables() {
        login = System.getenv("KEYCLOAK_LOGIN");
        password = System.getenv("KEYCLOAK_PASSWORD");
        url = System.getenv("KEYCLOAK_SERVER_URL");
        clientId = System.getenv("KEYCLOAK_CLIENT_ID");
        realm = System.getenv("KEYCLOAK_REALM");
    }

    static {
        initializeDataFromEnvironmentVariables();
    }

    private KeycloakCredentialsProvider(){}

}
