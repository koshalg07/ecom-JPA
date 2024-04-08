package com.example.ecom.dao;

import java.util.Optional;

import com.example.ecom.model.User;

public interface UserDAO {
    void register(User user);
    Optional<User> login(String username, String password);
}

