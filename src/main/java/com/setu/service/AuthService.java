package com.setu.service;

import com.setu.model.User;
import com.setu.repository.UserRepository;

public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) return false;

        return user.getPassword().equals(password);
    }
}