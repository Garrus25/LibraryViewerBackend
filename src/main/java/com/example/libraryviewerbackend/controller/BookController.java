package com.example.libraryviewerbackend.controller;

import com.example.libraryviewerbackend.service.BookService;
import com.openapi.gen.springboot.api.BookApiController;
import com.openapi.gen.springboot.dto.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class BookController extends BookApiController {

    BookService bookService;

    public BookController(BookService bookService) {
        super(null);
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<BookDTO> addBook(BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @Override
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @Override
    public ResponseEntity<BookDTO> getBookById(String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @Override
    public ResponseEntity<Void> deleteBookById(String id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Resource> getBookCover(String filename) {
        return ResponseEntity.ok(bookService.retrieveStaticPictureData(filename));
    }

    @Override
    public ResponseEntity<List<BookDTO>> getNewlyAddedBooks(Integer amount) {
        return ResponseEntity.ok(bookService.findNewlyAddedBooks(amount));
    }

    @Override
    public ResponseEntity<List<BookDTO>> getSpecifiedAmountOfBestRatedBooks(Integer amount) {
        return ResponseEntity.ok(bookService.findSpecifiedAmountOfBestRatedBooks(amount));
    }

    @Override
    public ResponseEntity<List<BookDTO>> getAllBooksCreatedBySpecificUser(String id) {
        return ResponseEntity.ok(bookService.getBooksCreatedBySpecificUser(id));
    }

    @Override
    public ResponseEntity<Void> uploadBookCover(MultipartFile file) {
        bookService.saveImage(file);
        return ResponseEntity.ok().build();
    }
}
