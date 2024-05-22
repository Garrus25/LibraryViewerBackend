package com.example.libraryviewerbackend.controller;

import com.example.libraryviewerbackend.service.AuthorService;
import com.openapi.gen.springboot.api.AuthorApiController;
import com.openapi.gen.springboot.dto.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController extends AuthorApiController {

    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        super(null);
        this.authorService = authorService;
    }

    @Override
    public ResponseEntity<AuthorDTO> addAuthor(AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.addAuthor(authorDTO));
    }

    @Override
    public ResponseEntity<Void> deleteAuthorById(Integer id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @Override
    public ResponseEntity<AuthorDTO> getAuthorBYId(Integer id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @Override
    public ResponseEntity<Resource> getAuthorPicture(String filename) {
        return ResponseEntity.ok(authorService.retrieveStaticPictureData(filename));
    }

    @Override
    public ResponseEntity<List<AuthorDTO>> getNewlyAddedAuthors(Integer amount) {
        return ResponseEntity.ok(authorService.findNewlyAddedAuthors(amount));
    }

    @Override
    public ResponseEntity<List<AuthorDTO>> getAllAuthorsCreatedBySpecificUser(String id) {
        return ResponseEntity.ok(authorService.getAuthorsCreatedBySpecificUser(id));
    }
}
