package com.example.libraryviewerbackend.service;

import com.openapi.gen.springboot.dto.SecurityEntity;
import com.openapi.gen.springboot.dto.UserCredentialsDTO;
import com.openapi.gen.springboot.dto.UserDTO;
import com.openapi.gen.springboot.dto.UserIdentityDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<UserDTO> saveUser(UserDTO userDTO);

    ResponseEntity<List<UserDTO>> getAllUsers();

    ResponseEntity<SecurityEntity> checkUserCredentials(UserCredentialsDTO userCredentialsDTO);

    Boolean checkIfEmailIsConfirmed(UserIdentityDTO userIdentityDTO);

    boolean resendEmailConfirmation(UserIdentityDTO userIdentityDTO);
}
