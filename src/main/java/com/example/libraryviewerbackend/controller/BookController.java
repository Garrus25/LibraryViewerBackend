package com.example.libraryviewerbackend.controller;

import com.example.libraryviewerbackend.service.BookService;
import com.openapi.gen.springboot.api.BookApiController;
import com.openapi.gen.springboot.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
