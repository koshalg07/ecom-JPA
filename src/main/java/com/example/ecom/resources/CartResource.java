package com.example.ecom.resources;

import java.util.List;

import com.example.ecom.dao.CartItemDAO;
import com.example.ecom.model.CartItem;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cart")
public class CartResource {
	private final CartItemDAO cartItemDAO = new CartItemDAO();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addItemToCart(CartItem cartItem) {
		cartItemDAO.addItemToCart(cartItem);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CartItem> viewCart(@PathParam("userId") int userId){
		return cartItemDAO.getCartItemsByUserId(userId);
	}
	
	@DELETE
	@Path("/{cartItemId}/{userId}/{productId}")
	public Response removeItemFromCart(@PathParam("cartItemId") int cartItemId, @PathParam("userId") int userId, @PathParam("productId") int productId) {
		cartItemDAO.removeItemFromCart(cartItemId,userId,productId);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	@POST
	@Path("/checkout")
	public Response checkout() {
		return Response.status(Response.Status.OK).build();
	}
}
