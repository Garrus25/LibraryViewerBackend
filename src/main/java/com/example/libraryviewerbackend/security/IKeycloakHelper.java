package com.example.libraryviewerbackend.security;

import com.openapi.gen.springboot.dto.SecurityEntity;
import com.openapi.gen.springboot.dto.UserCredentialsDTO;
import com.openapi.gen.springboot.dto.UserDTO;
import jakarta.ws.rs.core.Response;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IKeycloakHelper {
    Response createUser(UserDTO userDTO);

    List<UserRepresentation> getAllUsers();

    SecurityEntity checkUserCredentials(UserCredentialsDTO userCredentialsDTO);

    Boolean checkIfEmailIsConfirmed(String userId);

    boolean sendConfirmationEmail(String userId);
}
