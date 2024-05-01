package com.example.libraryviewerbackend.controller;

import com.example.libraryviewerbackend.service.UserService;
import com.openapi.gen.springboot.api.UserApiController;
import com.openapi.gen.springboot.dto.SecurityEntity;
import com.openapi.gen.springboot.dto.UserCredentialsDTO;
import com.openapi.gen.springboot.dto.UserDTO;
import com.openapi.gen.springboot.dto.UserIdentityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController extends UserApiController {

    UserService userService;

    public UserController(UserService userService) {
        super(null);
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public ResponseEntity<SecurityEntity> checkUserCredentials(UserCredentialsDTO userCredentialsDTO) {
        return userService.checkUserCredentials(userCredentialsDTO);
    }

    @Override
    public ResponseEntity<String> checkIfEmailIsConfirmed(UserIdentityDTO userIdentityDTO) {
        Boolean emailConfirmationStatus = userService.checkIfEmailIsConfirmed(userIdentityDTO);
        if (emailConfirmationStatus == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("User with id: %s not found", userIdentityDTO.getId()));
        }
        return ResponseEntity.ok(emailConfirmationStatus ? "true" : "false");
    }

    @Override
    public ResponseEntity<String> resendEmail(UserIdentityDTO userIdentityDTO) {
        boolean emailConfirmationStatus = userService.resendEmailConfirmation(userIdentityDTO);
        if (!emailConfirmationStatus) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("User with id: %s not found", userIdentityDTO.getId()));
        }
        return ResponseEntity.ok().build();
    }
}
