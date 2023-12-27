package com.example.libraryviewerbackend.repositoryadapter;

import com.example.libraryviewerbackend.model.User;
import com.example.libraryviewerbackend.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter {
    UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
