package com.example.libraryviewerbackend.repositoryadapter;

import com.example.libraryviewerbackend.model.Author;
import com.example.libraryviewerbackend.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorRepositoryAdapter {
    AuthorRepository authorRepository;

    public AuthorRepositoryAdapter(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer id){
        return authorRepository.findById(id).orElse(null);
    }

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    public void deleteAuthorById(Integer id){
        authorRepository.deleteById(id);
    }
}
