package com.example.ecom.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;

import com.example.ecom.dao.UserDAO;
import com.example.ecom.dto.UserDTO;
import com.example.ecom.model.User;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserDAO userDAO, ModelMapper modelMapper) {
        this.userDAO = userDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userDAO.register(user);
    }

    @Override
    public UserDTO login(String username, String password) {
        Optional<User> userOptional = userDAO.login(username, password);
        return userOptional.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);
    }
}
