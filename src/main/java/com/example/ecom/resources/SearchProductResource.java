package com.example.ecom.resources;

import java.util.List;

import com.example.ecom.dao.ProductDAO;
import com.example.ecom.model.Product;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/search")
public class SearchProductResource {
	private final ProductDAO productDAO = new ProductDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> searchProduct(@QueryParam("name") String name, @QueryParam("category") String category) {
		if(name != null && !name.isEmpty() && category != null && !category.isEmpty()) {
			return productDAO.searchProductsByNameAndCategory(name, category);
		}
		else if(name != null && !name.isEmpty()) {
			return productDAO.searchProductByName(name);
		}
		else if(category != null && !category.isEmpty()) {
			return productDAO.searchProductByCategory(category);
		}
		else {
			return ProductDAO.getAllProducts();
		}
	}
}
