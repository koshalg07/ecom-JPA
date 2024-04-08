package com.example.ecom.resource;

import com.example.ecom.dto.UserDTO;
import com.example.ecom.service.UserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
public class AuthResource {

    private UserService userService;

    //@Inject
    //public AuthResource(UserService userService) {
    //    this.userService = userService;
    //}

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(UserDTO userDTO) {
    	System.out.println("UserDTO = " + userDTO);
        userService.register(userDTO);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO login(UserDTO userDTO) {
        return userService.login(userDTO.getUsername().toString(), userDTO.getPassword().toString());
    }
}

