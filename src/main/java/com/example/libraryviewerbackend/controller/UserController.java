package com.example.libraryviewerbackend.controller;

import com.example.libraryviewerbackend.service.UserService;
import com.openapi.gen.springboot.api.UserApiController;
import com.openapi.gen.springboot.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends UserApiController {

    UserService userService;

    public UserController(UserService userService) {
        super(null);
        //TODO co zrobić z tym konstruktorem?
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

}
