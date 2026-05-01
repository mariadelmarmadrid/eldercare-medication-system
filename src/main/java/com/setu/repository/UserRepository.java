package com.setu.repository;

import com.setu.model.User;

public interface UserRepository {
    User findByEmail(String email);
}