package com.example.libraryviewerbackend.modelmapper;

import com.example.libraryviewerbackend.model.User;
import com.openapi.gen.springboot.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserModelMapper {
    UserModelMapper INSTANCE = Mappers.getMapper(UserModelMapper.class);

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}