package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.modelmapper.UserModelMapper;
import com.example.libraryviewerbackend.security.KeycloakHelper;
import com.openapi.gen.springboot.dto.SecurityEntity;
import com.openapi.gen.springboot.dto.UserCredentialsDTO;
import com.openapi.gen.springboot.dto.UserDTO;
import com.openapi.gen.springboot.dto.UserIdentityDTO;
import jakarta.ws.rs.core.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserService implements IUserService {

    KeycloakHelper keycloakHelper;

    public UserService(KeycloakHelper keycloakHelper) {
        this.keycloakHelper = keycloakHelper;
    }

    @Override
    public ResponseEntity<UserDTO> saveUser(UserDTO user) {
        try (Response response = keycloakHelper.createUser(user)){
            if (response.getStatus() == HttpStatus.CONFLICT.value()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        }
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok((keycloakHelper.getAllUsers()
                .stream()
                .map(UserModelMapper::toDto)
                .toList()));
    }

    @Override
    public ResponseEntity<SecurityEntity> checkUserCredentials(UserCredentialsDTO userCredentialsDTO) {
        SecurityEntity securityEntity = keycloakHelper.checkUserCredentials(userCredentialsDTO);
        if (!Objects.isNull(securityEntity)) {
            return ResponseEntity.status(HttpStatus.OK).body(securityEntity);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public Boolean checkIfEmailIsConfirmed(UserIdentityDTO userIdentityDTO) {
        return keycloakHelper.checkIfEmailIsConfirmed(userIdentityDTO.getId());
    }

    @Override
    public boolean resendEmailConfirmation(UserIdentityDTO userIdentityDTO) {
        return keycloakHelper.sendConfirmationEmail(userIdentityDTO.getId());
    }
}
