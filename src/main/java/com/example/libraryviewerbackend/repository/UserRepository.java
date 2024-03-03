package com.example.libraryviewerbackend.repository;

import com.example.libraryviewerbackend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends CrudRepository<User, Integer> {
    User getUserById(Integer id);

    @Query("SELECT max(u.id) FROM User u")
    Long getMaxId();
}
