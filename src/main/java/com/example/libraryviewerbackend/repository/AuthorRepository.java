package com.example.libraryviewerbackend.repository;

import com.example.libraryviewerbackend.model.Author;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends BaseRepository<Author, Integer> {
    @Query(value = "SELECT * FROM authors ORDER BY addition_date DESC LIMIT :amount", nativeQuery = true)
    List<Author> findSpecifiedAmountOfAuthorsOrderedByAdditionDate(int amount);

    List<Author> getAuthorByCreatedBy(String userId);

}