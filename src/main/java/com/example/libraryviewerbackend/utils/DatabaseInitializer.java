package com.example.libraryviewerbackend.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class DatabaseInitializer implements InitializingBean {
    private static final String CHECK_IF_DATABASE_EXISTS_QUERY = "SELECT 1 FROM pg_catalog.pg_database WHERE lower(datname) = lower('libraryviewerdb')";
    private static final String CREATE_DATABASE_QUERY = "CREATE DATABASE libraryviewerdb";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void afterPropertiesSet(){
        Query query = entityManager.createNativeQuery(CHECK_IF_DATABASE_EXISTS_QUERY);
        if (query.getSingleResult() != null) {
            log.info("Database already exists");
            return;
        }
        entityManager.createQuery(CREATE_DATABASE_QUERY);
        log.info("Database created successfully");
    }
}
