package com.example.libraryviewerbackend.service;

import com.openapi.gen.springboot.dto.AuthorDTO;
import org.springframework.core.io.Resource;

import java.util.List;

public interface IAuthorService {
    List<AuthorDTO> getAllAuthors();
    AuthorDTO getAuthorById(Integer id);
    void deleteAuthorById(Integer id);
    AuthorDTO addAuthor(AuthorDTO authorDTO);
    Resource retrieveStaticPictureData(String filename);
    List<AuthorDTO> findNewlyAddedAuthors(Integer amount);
}
