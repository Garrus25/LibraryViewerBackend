package com.example.libraryviewerbackend.repositoryadapter;

import com.example.libraryviewerbackend.model.User;
import com.example.libraryviewerbackend.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryAdapter {
    UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
