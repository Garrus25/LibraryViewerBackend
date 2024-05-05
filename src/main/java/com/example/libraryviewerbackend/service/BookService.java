package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.exceptions.CoverAccessException;
import com.example.libraryviewerbackend.exceptions.ObjectAlreadyExistsException;
import com.example.libraryviewerbackend.exceptions.ObjectNotFoundException;
import com.example.libraryviewerbackend.model.Book;
import com.example.libraryviewerbackend.modelmapper.BookModelMapper;
import com.example.libraryviewerbackend.repositoryadapter.BookRepositoryAdapter;
import com.example.libraryviewerbackend.utils.UserMessages;
import com.openapi.gen.springboot.dto.BookDTO;
import jakarta.ws.rs.BadRequestException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService implements IBookService, PictureRetriever{
    private final BookRepositoryAdapter bookRepositoryAdapter;
    private final ResourceLoader resourceLoader;

    public BookService(BookRepositoryAdapter bookRepositoryAdapter,
                       ResourceLoader resourceLoader) {
        this.bookRepositoryAdapter = bookRepositoryAdapter;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        if (!bookDTO.getIsbn().matches("\\d+")) {
            throw new BadRequestException(UserMessages.INVALID_ISBN);
        }
        if (bookRepositoryAdapter.getBookById(bookDTO.getIsbn()) != null) {
            throw new ObjectAlreadyExistsException(UserMessages.BOOK_WITH_ISBN_ALREADY_EXISTS, Long.valueOf(bookDTO.getIsbn()));
        }
        return BookModelMapper.INSTANCE.toDTO(bookRepositoryAdapter.addBook(BookModelMapper.INSTANCE.toEntity(bookDTO)));
    }

    @Override
    public BookDTO getBookById(String id) {
        if (id == null || !id.matches("\\d+")) {
            throw new BadRequestException(UserMessages.INVALID_ISBN);
        }
        if (bookRepositoryAdapter.getBookById(id) == null) {
            throw new ObjectNotFoundException(UserMessages.BOOK_WITH_SPEFICIED_ISBN_NOT_FOUND, Long.valueOf(id), Book.class);
        }
        return BookModelMapper.INSTANCE.toDTO(bookRepositoryAdapter.getBookById(id));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepositoryAdapter.getAllBooks();
        return books.stream().map(BookModelMapper.INSTANCE::toDTO).toList();
    }

    @Override
    public void deleteBookById(String id) {
        if (id == null || !id.matches("\\d+")) {
            throw new BadRequestException(UserMessages.INVALID_ISBN);
        }

        if (bookRepositoryAdapter.getBookById(id) == null) {
            throw new ObjectNotFoundException(UserMessages.BOOK_WITH_SPEFICIED_ISBN_NOT_FOUND, Long.valueOf(id), Book.class);
        }

        bookRepositoryAdapter.deleteBookById(id);
    }

    @Override
    public Resource retrieveStaticPictureData(String filename) {
        Resource resource = resourceLoader.getResource("classpath:media/book-covers/" + filename);
        try {
            if (!resource.exists() || !resource.isReadable()) {
                throw new CoverAccessException(UserMessages.COULD_NOT_FIND_SPECIFIED_FILE);
            }
        } catch (Exception e) {
            throw new CoverAccessException(UserMessages.INTERNAL_SERVER_ERROR_WHEN_ACCESSING_COVER);
        }
        return resource;
    }

    @Override
    public List<BookDTO> findNewlyAddedBooks(Integer amount){
        List<Book> books = bookRepositoryAdapter.findNewlyAddedBooks(amount);
        return books.stream().map(BookModelMapper.INSTANCE::toDTO).toList();
    }
}
