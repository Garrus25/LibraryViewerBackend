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
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
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
        List<BookDTO> books = bookRepositoryAdapter.getAllBooks().stream()
                .map(BookModelMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        books.forEach(book -> book.setAverageRating(new BigDecimal(String.valueOf(book.getAverageRating())).setScale(2, RoundingMode.HALF_UP)));

        return books;
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

    @Override
    public List<BookDTO> findSpecifiedAmountOfBestRatedBooks(Integer amount) {
        List<BookDTO> books = bookRepositoryAdapter.findBestRatedBooks(amount).stream()
                .map(BookModelMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        books.forEach(book -> book.setAverageRating(new BigDecimal(String.valueOf(book.getAverageRating())).setScale(2, RoundingMode.HALF_UP)));

        return books;
    }


    @Override
    public List<BookDTO> getBooksCreatedBySpecificUser(String userId) {
        return bookRepositoryAdapter.getAllBooksCreatedBySpecificUser(userId).stream()
                .map(BookModelMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean saveImage(MultipartFile file) {
        try {
            Path directoryPath = Paths.get("src/main/resources/media/book-covers");
            Path filePath = directoryPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.write(filePath, file.getBytes());
            return true;
        } catch (IOException e) {
            log.error("Error while saving image", e);
            return false;
        }
    }
}
