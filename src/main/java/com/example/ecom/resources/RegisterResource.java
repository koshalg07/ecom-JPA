package com.example.ecom.resources;

import com.example.ecom.dao.UserDAO;
import com.example.ecom.model.User;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/register")
public class RegisterResource {
	private final UserDAO userDAO = new UserDAO();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(User user) {
		userDAO.save(user);
		System.out.println("User registered");
		return Response.status(Response.Status.CREATED).build();
	}
}
