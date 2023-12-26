package com.example.libraryviewerbackend.utils;

import com.example.libraryviewerbackend.exceptions.LiquibasePropertiesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Configuration
public class LiquibasePropertiesFileGenerator implements InitializingBean {
    private static final String PROPERTY_FILE_NAME = "liquibase.properties";
    private static final Path PROPERTY_FILE_LOCATION = Path.of("src/main/resources/" + PROPERTY_FILE_NAME);
    private static final String LIQUIBASE_URL_VARIABLE = "LIQUIBASE_COMMAND_URL";
    private static final String LIQUIBASE_PASSWORD_VARIABLE = "LIQUIBASE_COMMAND_PASSWORD";
    private static final String LIQUIBASE_USERNAME_VARIABLE = "LIQUIBASE_COMMAND_USERNAME";
    private static final String CHANGE_LOG_FILE_NAME = "src/main/resources/changelog.xml";

    @Override
    public void afterPropertiesSet() {
        createPropertiesFile();
        insertPropertiesIntoFile();
    }

    private void createPropertiesFile() {
        if (Files.exists(PROPERTY_FILE_LOCATION)) {
            try {
                Files.delete(PROPERTY_FILE_LOCATION);
            } catch (IOException e) {
                log.error("Could not delete existing liquibase property file");
                throw new LiquibasePropertiesException("Could not delete existing liquibase property file");
            }
        }

        try {
            Files.createFile(PROPERTY_FILE_LOCATION);
        } catch (IOException e) {
            log.error("Could not create liquibase property file");
            throw new LiquibasePropertiesException("Could not create liquibase property file");
        }
        log.info("Liquibase property file created");
    }

    private void insertPropertiesIntoFile() {
        File file = new File(PROPERTY_FILE_LOCATION.toString());
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(String.format("changeLogFile:%s%n", CHANGE_LOG_FILE_NAME));
            fileWriter.write(String.format("url:%s%n", System.getenv(LIQUIBASE_URL_VARIABLE)));
            fileWriter.write(String.format("username:%s%n", System.getenv(LIQUIBASE_USERNAME_VARIABLE)));
            fileWriter.write(String.format("password:%s%n", System.getenv(LIQUIBASE_PASSWORD_VARIABLE)));
        } catch (IOException e) {
            log.error("Could not write properties into liquibase property file");
            throw new LiquibasePropertiesException("Could not write properties into liquibase property file");
        }
    }
}
