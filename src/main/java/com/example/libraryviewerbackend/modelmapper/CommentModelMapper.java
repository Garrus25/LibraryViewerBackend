package com.example.libraryviewerbackend.modelmapper;

import com.example.libraryviewerbackend.model.Comment;
import com.openapi.gen.springboot.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentModelMapper {
    CommentModelMapper INSTANCE = Mappers.getMapper(CommentModelMapper.class);

    CommentDTO toDTO(Comment author);

    Comment toEntity(CommentDTO authorDTO);
}