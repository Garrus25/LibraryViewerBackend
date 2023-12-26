package com.example.libraryviewerbackend.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.sql.*;


@Configuration
@Slf4j
public class DatabaseInitializer implements InitializingBean {
    private static final String CHECK_IF_DATABASE_EXISTS_QUERY = "SELECT 1 FROM pg_catalog.pg_database WHERE lower(datname) = lower('libraryviewerdb')";
    private static final String CREATE_DATABASE_QUERY = "CREATE DATABASE libraryviewerdb";

    @Override
    public void afterPropertiesSet(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://postgresdb:5432/libraryViewerDatabase",
                    DatabaseCredentialsProvider.getLogin(), DatabaseCredentialsProvider.getPassword());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(CHECK_IF_DATABASE_EXISTS_QUERY);
            if (resultSet.next()){
                log.info("Database already exists");
            }else {
                statement.executeUpdate(CREATE_DATABASE_QUERY);
                log.info("Database created successfully");
            }
        } catch (SQLException e) {
            log.error(String.format("test:  %s", e.getMessage()));
        }

    }
}