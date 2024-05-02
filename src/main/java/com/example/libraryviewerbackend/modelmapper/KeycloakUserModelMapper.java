package com.example.libraryviewerbackend.modelmapper;

import com.openapi.gen.springboot.dto.UserDTO;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public class KeycloakUserModelMapper {
    private KeycloakUserModelMapper() {
    }

    public static UserDTO toDto(UserRepresentation userRepresentation) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userRepresentation.getUsername());
        userDTO.setEmail(userRepresentation.getEmail());
        userDTO.setId(userRepresentation.getId());
        return userDTO;
    }

    public static UserRepresentation toEntity(UserDTO userDTO) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userDTO.getUsername());
        userRepresentation.setEmail(userDTO.getEmail());
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userDTO.getPassword());
        credentialRepresentation.setTemporary(false);
        userRepresentation.setCredentials(List.of(credentialRepresentation));
        return userRepresentation;
    }
}
