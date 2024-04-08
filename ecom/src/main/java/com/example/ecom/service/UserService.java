package com.example.ecom.service;

import com.example.ecom.dto.UserDTO;

public interface UserService {
    void register(UserDTO userDTO);
    UserDTO login(String username, String password);
}


