package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.model.User;
import com.example.libraryviewerbackend.modelmapper.UserModelMapper;
import com.example.libraryviewerbackend.repositoryadapter.UserRepositoryAdapter;
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

    UserRepositoryAdapter userRepositoryAdapter;

    public UserService(KeycloakHelper keycloakHelper, UserRepositoryAdapter userRepositoryAdapter) {
        this.keycloakHelper = keycloakHelper;
        this.userRepositoryAdapter = userRepositoryAdapter;
    }

    @Override
    public ResponseEntity<UserDTO> saveUser(UserDTO userDTO) {
        User user;
        try (Response response = keycloakHelper.createUser(userDTO)) {
            if (response.getStatus() == HttpStatus.CONFLICT.value()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            } else {
                userDTO.setId(response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1"));
                user = userRepositoryAdapter.saveUser(UserModelMapper.INSTANCE.toEntity(userDTO));
            }
        }
        return ResponseEntity.ok(UserModelMapper.INSTANCE.toDTO(user));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userRepositoryAdapter.findAll().stream().map(UserModelMapper.INSTANCE::toDTO).toList());
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
