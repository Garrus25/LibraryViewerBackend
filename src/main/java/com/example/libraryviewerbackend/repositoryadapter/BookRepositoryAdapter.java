package com.example.libraryviewerbackend.repositoryadapter;

import com.example.libraryviewerbackend.model.Book;
import com.example.libraryviewerbackend.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRepositoryAdapter {
    BookRepository bookRepository;

    public BookRepositoryAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(String id){
        return bookRepository.findById(id).orElse(null);
    }
}
