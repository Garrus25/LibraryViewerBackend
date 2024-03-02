package com.example.libraryviewerbackend.controller;

import com.example.libraryviewerbackend.service.UserService;
import com.openapi.gen.springboot.api.UserApiController;
import com.openapi.gen.springboot.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController extends UserApiController {

    UserService userService;

    public UserController(UserService userService) {
        super(null);
        //TODO co zrobić z tym konstruktorem?
        //TODO pełen CRUD dla usera
        //TODO wrzucenie postgresa na dockera
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @Override
    public ResponseEntity<UserDTO> createUserWithId(Integer id, UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUserWithId(userDTO, id));
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
