package com.example.libraryviewerbackend.modelmapper;

import com.example.libraryviewerbackend.model.Book;
import com.openapi.gen.springboot.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookModelMapper {
    BookModelMapper INSTANCE = Mappers.getMapper(BookModelMapper.class);

    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);
}