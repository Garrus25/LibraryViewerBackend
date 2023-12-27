package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.modelmapper.UserModelMapper;
import com.example.libraryviewerbackend.repositoryadapter.UserRepositoryAdapter;
import com.openapi.gen.springboot.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    UserRepositoryAdapter userRepositoryAdapter;

    public UserService(UserRepositoryAdapter userRepositoryAdapter) {
        this.userRepositoryAdapter = userRepositoryAdapter;
    }

    public UserDTO saveUser(UserDTO user) {
        return UserModelMapper.INSTANCE.toDTO(userRepositoryAdapter.saveUser(UserModelMapper.INSTANCE.toEntity(user)));
    }
}
