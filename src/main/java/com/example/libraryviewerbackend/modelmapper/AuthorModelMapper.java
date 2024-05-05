package com.example.libraryviewerbackend.modelmapper;

import com.example.libraryviewerbackend.model.Author;
import com.openapi.gen.springboot.dto.AuthorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorModelMapper {
    AuthorModelMapper INSTANCE = Mappers.getMapper(AuthorModelMapper.class);

    AuthorDTO toDTO(Author author);

    Author toEntity(AuthorDTO authorDTO);
}