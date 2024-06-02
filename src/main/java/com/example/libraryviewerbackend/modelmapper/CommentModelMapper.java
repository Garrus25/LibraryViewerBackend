package com.example.libraryviewerbackend.modelmapper;

import com.example.libraryviewerbackend.model.Comment;
import com.openapi.gen.springboot.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Mapper
public interface CommentModelMapper {
    CommentModelMapper INSTANCE = Mappers.getMapper(CommentModelMapper.class);

    CommentDTO toDTO(Comment author);

    @Mapping(target = "createdAt", expression = "java(convertToEntityCreatedAt(authorDTO.getCreatedAt()))")
    Comment toEntity(CommentDTO authorDTO);

    default LocalDateTime convertToEntityCreatedAt(String createdAt) {
        return OffsetDateTime.parse(createdAt).toLocalDateTime();
    }
}