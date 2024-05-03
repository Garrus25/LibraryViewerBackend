package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.exceptions.ObjectAlreadyExistsException;
import com.example.libraryviewerbackend.exceptions.ObjectNotFoundException;
import com.example.libraryviewerbackend.model.Book;
import com.example.libraryviewerbackend.modelmapper.BookModelMapper;
import com.example.libraryviewerbackend.repositoryadapter.BookRepositoryAdapter;
import com.openapi.gen.springboot.dto.BookDTO;
import jakarta.ws.rs.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService implements IBookService {
    BookRepositoryAdapter bookRepositoryAdapter;

    public BookService(BookRepositoryAdapter bookRepositoryAdapter) {
        this.bookRepositoryAdapter = bookRepositoryAdapter;
    }

    public BookDTO addBook(BookDTO bookDTO) {
        if (!bookDTO.getIsbn().matches("\\d+")) {
            throw new BadRequestException("Invalid format of ISBN");
        }
        if (bookRepositoryAdapter.getBookById(bookDTO.getIsbn()) != null){
            throw new ObjectAlreadyExistsException("Book with isbn: %s already exists", Long.valueOf(bookDTO.getIsbn()));
        }
        return BookModelMapper.INSTANCE.toDTO(bookRepositoryAdapter.addBook(BookModelMapper.INSTANCE.toEntity(bookDTO)));
    }

    public BookDTO getBookById(String id){
        if (id == null || !id.matches("\\d+")){
            throw new BadRequestException("Invalid format of ISBN");
        }
        if (bookRepositoryAdapter.getBookById(id) == null){
            throw new ObjectNotFoundException("Book with isbn: %d not found", Long.valueOf(id), Book.class);
        }
        return BookModelMapper.INSTANCE.toDTO(bookRepositoryAdapter.getBookById(id));
    }

    public List<BookDTO> getAllBooks(){
        List<Book> books = bookRepositoryAdapter.getAllBooks();
        return books.stream().map(BookModelMapper.INSTANCE::toDTO).toList();
    }

}
