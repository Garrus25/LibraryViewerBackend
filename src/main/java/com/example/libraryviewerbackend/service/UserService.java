package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.modelmapper.UserModelMapper;
import com.example.libraryviewerbackend.security.KeycloakHelper;
import com.openapi.gen.springboot.dto.SecurityEntity;
import com.openapi.gen.springboot.dto.UserCredentialsDTO;
import com.openapi.gen.springboot.dto.UserDTO;
import jakarta.ws.rs.core.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserService {

    KeycloakHelper keycloakHelper;

    public UserService(KeycloakHelper keycloakHelper) {
        this.keycloakHelper = keycloakHelper;
    }

    public ResponseEntity<UserDTO> saveUser(UserDTO user) {
        try (Response response = keycloakHelper.createUser(user)){
            if (response.getStatus() == HttpStatus.CONFLICT.value()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        }
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok((keycloakHelper.getAllUsers()
                .stream()
                .map(UserModelMapper::toDto)
                .toList()));
    }

    public ResponseEntity<SecurityEntity> checkUserCredentials(UserCredentialsDTO userCredentialsDTO) {
        SecurityEntity securityEntity = keycloakHelper.checkUserCredentials(userCredentialsDTO);
        if (!Objects.isNull(securityEntity)) {
            return ResponseEntity.status(HttpStatus.OK).body(securityEntity);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
