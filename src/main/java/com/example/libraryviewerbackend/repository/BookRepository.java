package com.example.libraryviewerbackend.repository;

import com.example.libraryviewerbackend.model.Book;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends BaseRepository<Book, String> {
    @Query(value = "SELECT * FROM books ORDER BY addition_date DESC LIMIT :amount", nativeQuery = true)
    List<Book> findSpecifiedAmountOfBooksOrderedByAdditionDate(int amount);

    @Query(value = "SELECT * FROM books ORDER BY average_rate DESC LIMIT :amount", nativeQuery = true)
    List<Book> findSpecifiedAmountOfBestRatedBooks(int amount);

    List<Book> getBookByCreatedBy(String userId);

}