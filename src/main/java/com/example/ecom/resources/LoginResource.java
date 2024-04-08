package com.example.ecom.resources;

import com.example.ecom.dao.UserDAO;
import com.example.ecom.model.User;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginResource {
	
	private final UserDAO userDAO = new UserDAO();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUser(User user) {
		User existingUser = userDAO.getUserByUsername(user.getUsername());
		if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
			System.out.println("Login Successful");
			return Response.status(Response.Status.OK).entity("Login Successful").build();
		}
		else {
			System.out.println("Login failed");
			return Response.status(Response.Status.UNAUTHORIZED).entity("Login failed").build();
		}
	}
	
}
