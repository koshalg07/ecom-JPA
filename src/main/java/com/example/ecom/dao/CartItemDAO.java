package com.example.ecom.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.ecom.config.DatabaseConfig;
import com.example.ecom.model.CartItem;

import jakarta.ws.rs.NotFoundException;



public class CartItemDAO {
	
	public void addItemToCart(CartItem cartItem) {
		try(Session session = DatabaseConfig.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			session.persist(cartItem);
			transaction.commit();
		}
	}
	
	public List<CartItem> getCartItemsByUserId(int userId){
		try(Session session = DatabaseConfig.getSessionFactory().openSession()){
			Query<CartItem> query = session.createQuery("FROM CartItem WHERE userId = :userId", CartItem.class)
					.setParameter("userId", userId);
			return query.list();
		}
	}
	
	public void removeItemFromCart(int cartItemId,int userId, int productId) {
		try(Session session = DatabaseConfig.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			Query<CartItem> query = session.createQuery("FROM CartItem WHERE id = :cartItemId AND userId = :userId AND productId = :productId", CartItem.class)
					.setParameter("cartItemId", cartItemId)
					.setParameter("userId", userId)
					.setParameter("productId", productId);
			CartItem cartItem = query.uniqueResult();
			if(cartItem != null) {
				session.delete(cartItem);
				
			}
			else {
				throw new NotFoundException("Cart Item not Found for user: " + userId + " with ID: " + cartItemId);
			}
			transaction.commit();
		}
	}
}
