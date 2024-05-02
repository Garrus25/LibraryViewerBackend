package com.example.libraryviewerbackend.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
interface BaseRepository<T, E> extends Repository<T, E> {

    Optional<T> findById(E id);

    <S extends T> S save(S entity);

    List<T> findAll();
}