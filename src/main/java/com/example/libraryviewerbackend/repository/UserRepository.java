package com.example.libraryviewerbackend.repository;

import com.example.libraryviewerbackend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserRepository extends CrudRepository<User, Integer> {
}
