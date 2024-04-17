package com.example.libraryviewerbackend.security;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KeycloakHelper {
    private static final Logger logger = LoggerFactory.getLogger(KeycloakHelper.class);

    private static final String REALM_NAME = "master";

    private final Keycloak keycloak;

    public KeycloakHelper(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    void searchByUsername(String username, boolean exact) {
        logger.info("Searching by username: {} (exact {})", username, exact);
        List<UserRepresentation> users = keycloak.realm(REALM_NAME)
                .users()
                .searchByUsername(username, exact);

        logger.info("Users found by username {}", users.stream()
                .map(user -> user.getUsername())
                .collect(Collectors.toList()));
    }

    public List<UserRepresentation> getAllUsers(){
        return keycloak.realm(REALM_NAME)
                .users().search("");
    }
}
