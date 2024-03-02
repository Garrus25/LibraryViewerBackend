package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.exceptions.ObjectAlreadyExistsException;
import com.example.libraryviewerbackend.exceptions.PathAndBodyIdMismatchException;
import com.example.libraryviewerbackend.modelmapper.UserModelMapper;
import com.example.libraryviewerbackend.repositoryadapter.UserRepositoryAdapter;
import com.example.libraryviewerbackend.utils.UserMessages;
import com.openapi.gen.springboot.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Component
public class UserService {

    UserRepositoryAdapter userRepositoryAdapter;

    public UserService(UserRepositoryAdapter userRepositoryAdapter) {
        this.userRepositoryAdapter = userRepositoryAdapter;
    }

    public UserDTO saveUser(UserDTO user) {
        if (user.getId() == null) {
            user.setId(Math.toIntExact(userRepositoryAdapter.getMaxId() + 1));
        }
        if (userRepositoryAdapter.getUserById(user.getId()) != null) {
            throw new ObjectAlreadyExistsException(UserMessages.USER_WITH_SPECIFIED_ID_ALREADY_EXISTS_MESSAGE);
        }
        return UserModelMapper.INSTANCE.toDTO(userRepositoryAdapter.saveUser(UserModelMapper.INSTANCE.toEntity(user)));
    }

    public UserDTO saveUserWithId(UserDTO user, Integer id) {
        if (!Objects.equals(user.getId(), id)) {
            throw new PathAndBodyIdMismatchException(UserMessages.ID_MISMATCH_IN_PATH_AND_BODY_MESSAGE);
        }
        if (userRepositoryAdapter.getUserById(user.getId()) != null) {
            throw new ObjectAlreadyExistsException(UserMessages.USER_WITH_SPECIFIED_ID_ALREADY_EXISTS_MESSAGE);
        }
        return UserModelMapper.INSTANCE.toDTO(userRepositoryAdapter.saveUser(UserModelMapper.INSTANCE.toEntity(user)));
    }

    public UserDTO getUserById(Integer id) {
        return UserModelMapper.INSTANCE.toDTO(userRepositoryAdapter.getUserById(id));
    }

    public List<UserDTO> getAllUsers() {
       return StreamSupport
                .stream(userRepositoryAdapter.getAllUsers().spliterator(), false)
                .map(UserModelMapper.INSTANCE::toDTO)
                .toList();
    }

    public void deleteUserById(Integer id) {
        userRepositoryAdapter.deleteUserById(id);
    }
}
