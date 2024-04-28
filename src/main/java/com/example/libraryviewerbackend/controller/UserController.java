package com.example.libraryviewerbackend.controller;

import com.example.libraryviewerbackend.service.UserService;
import com.openapi.gen.springboot.api.UserApiController;
import com.openapi.gen.springboot.dto.SecurityEntity;
import com.openapi.gen.springboot.dto.UserCredentialsDTO;
import com.openapi.gen.springboot.dto.UserDTO;
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
}
