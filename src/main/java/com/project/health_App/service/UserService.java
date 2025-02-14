package com.project.health_App.service;

import org.springframework.stereotype.Service;
import com.project.health_App.entities.User;
import com.project.health_App.repositories.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public User registerUser(User user) {
        return userRepository.save(user);
    }

   
    public boolean login(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password))
                .orElse(false); 
    }
}
