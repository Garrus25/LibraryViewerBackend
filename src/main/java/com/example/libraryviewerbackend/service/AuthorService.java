package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.exceptions.CoverAccessException;
import com.example.libraryviewerbackend.exceptions.ObjectAlreadyExistsException;
import com.example.libraryviewerbackend.exceptions.ObjectNotFoundException;
import com.example.libraryviewerbackend.model.Author;
import com.example.libraryviewerbackend.modelmapper.AuthorModelMapper;
import com.example.libraryviewerbackend.repositoryadapter.AuthorRepositoryAdapter;
import com.example.libraryviewerbackend.utils.UserMessages;
import com.openapi.gen.springboot.dto.AuthorDTO;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AuthorService implements IAuthorService, PictureRetriever {
    private final AuthorRepositoryAdapter authorRepositoryAdapter;
    private final ResourceLoader resourceLoader;

    public AuthorService(AuthorRepositoryAdapter authorRepositoryAdapter, ResourceLoader resourceLoader) {
        this.authorRepositoryAdapter = authorRepositoryAdapter;
        this.resourceLoader = resourceLoader;
    }

    public List<AuthorDTO> getAllAuthors(){
        return authorRepositoryAdapter.getAllAuthors().stream().map(AuthorModelMapper.INSTANCE::toDTO).toList();
    }

    public AuthorDTO getAuthorById(Integer id){
        if (Objects.isNull(authorRepositoryAdapter.getAuthorById(id))){
            throw new ObjectNotFoundException(UserMessages.AUTHOR_WITH_SPECIFIED_ID_NOT_FOUND, Long.valueOf(id), Author.class);
        }
        return AuthorModelMapper.INSTANCE.toDTO(authorRepositoryAdapter.getAuthorById(id));
    }

    public void deleteAuthorById(Integer id){
        if (Objects.isNull(authorRepositoryAdapter.getAuthorById(id))){
            throw new ObjectNotFoundException(UserMessages.AUTHOR_WITH_SPECIFIED_ID_NOT_FOUND, Long.valueOf(id), Author.class);
        }
        authorRepositoryAdapter.deleteAuthorById(id);
    }

    public AuthorDTO addAuthor(AuthorDTO authorDTO){
        if (authorRepositoryAdapter.getAuthorById(authorDTO.getAuthorId()) != null) {
            throw new ObjectAlreadyExistsException("Author with specified ID already exists", Long.valueOf(authorDTO.getAuthorId()));
        }

        return AuthorModelMapper.INSTANCE.toDTO(authorRepositoryAdapter.addAuthor(AuthorModelMapper.INSTANCE.toEntity(authorDTO)));
    }

    @Override
    public Resource retrieveStaticPictureData(String filename) {
        Resource resource = resourceLoader.getResource("classpath:media/author-pictures/" + filename);
        try {
            if (!resource.exists() || !resource.isReadable()) {
                throw new CoverAccessException(UserMessages.COULD_NOT_FIND_SPECIFIED_FILE);
            }
        } catch (Exception e) {
            throw new CoverAccessException(UserMessages.INTERNAL_SERVER_ERROR_WHEN_ACCESSING_COVER);
        }
        return resource;
    }

    public List<AuthorDTO> findNewlyAddedAuthors(Integer amount){
        return authorRepositoryAdapter.findNewlyAddedAuthors(amount).stream().map(AuthorModelMapper.INSTANCE::toDTO).toList();
    }

    @Override
    public List<AuthorDTO> getAuthorsCreatedBySpecificUser(String id) {
        return authorRepositoryAdapter.getAuthorsCreatedBySpecificUser(id).stream().map(AuthorModelMapper.INSTANCE::toDTO).toList();
    }
}
