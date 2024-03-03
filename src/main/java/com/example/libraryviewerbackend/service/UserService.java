package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.exceptions.ObjectAlreadyExistsException;
import com.example.libraryviewerbackend.exceptions.ObjectNotFoundException;
import com.example.libraryviewerbackend.exceptions.PathAndBodyIdMismatchException;
import com.example.libraryviewerbackend.model.User;
import com.example.libraryviewerbackend.modelmapper.UserModelMapper;
import com.example.libraryviewerbackend.repositoryadapter.UserDataAccessAdapter;
import com.example.libraryviewerbackend.utils.UserMessages;
import com.openapi.gen.springboot.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Component
public class UserService {

    UserDataAccessAdapter userDataAccessAdapter;

    public UserService(UserDataAccessAdapter userDataAccessAdapter) {
        this.userDataAccessAdapter = userDataAccessAdapter;
    }

    public UserDTO saveUser(UserDTO user) {
        if (user.getId() == null) {
            if (userDataAccessAdapter.getMaxId() > 0) {
                user.setId(Math.toIntExact(userDataAccessAdapter.getMaxId() + 1));
            } else {
                user.setId(1);
            }
        }
        if (userDataAccessAdapter.getUserById(user.getId()) != null) {
            throw new ObjectAlreadyExistsException(UserMessages.USER_WITH_SPECIFIED_ID_ALREADY_EXISTS_MESSAGE, user.getId());
        }
        return UserModelMapper.INSTANCE.toDTO(userDataAccessAdapter.saveUser(UserModelMapper.INSTANCE.toEntity(user)));
    }

    public UserDTO saveUserWithId(UserDTO user, Integer pathId) {
        if (!Objects.equals(user.getId(), pathId)) {
            throw new PathAndBodyIdMismatchException(UserMessages.ID_MISMATCH_IN_PATH_AND_BODY_MESSAGE, pathId, user.getId());
        }
        if (userDataAccessAdapter.getUserById(user.getId()) != null) {
            throw new ObjectAlreadyExistsException(UserMessages.USER_WITH_SPECIFIED_ID_ALREADY_EXISTS_MESSAGE, user.getId());
        }
        return UserModelMapper.INSTANCE.toDTO(userDataAccessAdapter.saveUser(UserModelMapper.INSTANCE.toEntity(user)));
    }

    public UserDTO getUserById(Integer id) {
        UserDTO userDTO = UserModelMapper.INSTANCE.toDTO(userDataAccessAdapter.getUserById(id));
        if (userDTO == null) {
            throw new ObjectNotFoundException(UserMessages.OBJECT_NOT_FOUND_ERROR_MESSAGE, id, User.class);
        }
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
       return StreamSupport.stream(userDataAccessAdapter.getAllUsers().spliterator(), false)
                .map(UserModelMapper.INSTANCE::toDTO)
                .toList();
    }

    public void deleteUserById(Integer id) {
        if (Objects.isNull(userDataAccessAdapter.getUserById(id))) {
            throw new ObjectNotFoundException(UserMessages.USER_WITH_SPECIFIED_ID_ALREADY_EXISTS_MESSAGE, id, User.class);
        }
        userDataAccessAdapter.deleteUserById(id);
    }
}
