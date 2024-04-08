package com.example.ecom.resources;

import java.util.List;

import com.example.ecom.dao.ProductDAO;
import com.example.ecom.model.Product;
import com.google.gson.Gson;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sort")
public class SortResource {
	private Gson gson = new Gson();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response sortProductsByPrice() {
		try {
			List<Product> products = ProductDAO.getAllProducts();
			
			products.sort((p1,p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
			
			String json = gson.toJson(products);
			
			return Response.ok(json).build();
			
		} catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error sorting products").build();
		}
	}
}
