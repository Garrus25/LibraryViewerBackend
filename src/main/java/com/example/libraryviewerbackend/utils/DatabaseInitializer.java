package com.example.libraryviewerbackend.utils;

import lombok.extern.slf4j.Slf4j;
import java.sql.*;


@Slf4j
public class DatabaseInitializer{
    private static final String CHECK_IF_DATABASE_EXISTS_QUERY = "SELECT 1 FROM pg_catalog.pg_database WHERE lower(datname) = lower('libraryviewerdb')";
    private static final String CREATE_DATABASE_QUERY = "CREATE DATABASE libraryviewerdb";

    private DatabaseInitializer(){}

    public static void initializeDatabase(){
        try {
            Connection connection = DriverManager.getConnection(DatabaseCredentialsProvider.getUrl(),
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
            log.error(String.format("Error while creating database:  %s", e.getMessage()));
        }

    }
}
