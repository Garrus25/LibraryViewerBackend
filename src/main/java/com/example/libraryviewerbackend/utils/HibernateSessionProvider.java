package com.example.libraryviewerbackend.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionProvider {

    private HibernateSessionProvider() {
    }

    private static final String DATABASE_NAME = "libraryviewerdb";

    public static Session getHibernateSession() {
        final SessionFactory sf = getConfigurationFile().buildSessionFactory();
        return sf.openSession();
    }

    private static Configuration getConfigurationFile() {
        Configuration configuration = new Configuration().configure("criteria.cfg.xml");
        configuration.setProperty("hibernate.connection.url", DatabaseCredentialsProvider.getUrl() + DATABASE_NAME);
        configuration.setProperty("hibernate.connection.username", DatabaseCredentialsProvider.getLogin());
        configuration.setProperty("hibernate.connection.password", DatabaseCredentialsProvider.getPassword());
        return configuration;
    }
}