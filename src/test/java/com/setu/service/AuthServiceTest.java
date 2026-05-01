package com.setu.service;

import com.setu.model.User;
import com.setu.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private UserRepository userRepository;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        authService = new AuthService(userRepository);
    }

    @Test
    void shouldLoginSuccessfully() {
        User user = new User("test@email.com", "1234");

        when(userRepository.findByEmail("test@email.com")).thenReturn(user);

        boolean result = authService.login("test@email.com", "1234");

        assertTrue(result);
    }

    @Test
    void shouldFailLogin() {
        User user = new User("test@email.com", "1234");

        when(userRepository.findByEmail("test@email.com")).thenReturn(user);

        boolean result = authService.login("test@email.com", "wrong");

        assertFalse(result);
    }
    @Test
    void shouldFailLogin_whenUserDoesNotExist() {

        when(userRepository.findByEmail("notfound@email.com"))
                .thenReturn(null);

        boolean result = authService.login("notfound@email.com", "1234");

        assertFalse(result);
    }
}