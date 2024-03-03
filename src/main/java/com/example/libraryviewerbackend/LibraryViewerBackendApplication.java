package com.example.libraryviewerbackend;

import com.example.libraryviewerbackend.utils.DatabaseInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryViewerBackendApplication {

    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();
        SpringApplication.run(LibraryViewerBackendApplication.class, args);
    }

}
