package com.example.libraryviewerbackend.repositoryadapter;

import com.example.libraryviewerbackend.model.User;
import com.example.libraryviewerbackend.repository.UserCriteriaQueries;
import com.example.libraryviewerbackend.repository.UserRepository;
import org.springframework.stereotype.Component;


@Component
public class UserDataAccessAdapter {
    UserRepository userRepository;

    UserCriteriaQueries userCriteriaQueries;

    public UserDataAccessAdapter(UserRepository userRepository, UserCriteriaQueries userCriteriaQueries) {
        this.userRepository = userRepository;
        this.userCriteriaQueries = userCriteriaQueries;
    }

    public User getUserById(Integer id) {
        return userCriteriaQueries.getUserById(id);
    }

    public Iterable<User> getAllUsers() {
        return userCriteriaQueries.getAllUsers();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    public Integer getMaxId() {
        return userCriteriaQueries.getMaxId();
    }
}
