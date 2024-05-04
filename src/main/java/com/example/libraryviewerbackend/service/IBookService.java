package com.example.libraryviewerbackend.service;

import com.openapi.gen.springboot.dto.BookDTO;
import org.springframework.core.io.Resource;

import java.util.List;

public interface IBookService {
    BookDTO addBook(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    BookDTO getBookById(String id);

    void deleteBookById(String id);

    Resource getBookCoverWithSpecifiedFilename(String filename);

    List<BookDTO> findNewlyAddedBooks(Integer amount);
}
