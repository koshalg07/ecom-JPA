package com.example.ecom.resources;

import com.example.ecom.dao.UserDAO;
import com.example.ecom.model.User;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/resetPassword")
public class ResetPasswordResource {
	private final UserDAO userDAO = new UserDAO();
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response resetPassword(User user) {
		User existingUser = userDAO.getUserByUsername(user.getUsername());
		if(existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
			existingUser.setPassword(user.getNewPassword());
			userDAO.save(existingUser);
			return Response.status(Response.Status.OK).build();
		}
		else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
}
