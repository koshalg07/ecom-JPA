package com.example.ecom.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.ecom.config.DatabaseConfig;
import com.example.ecom.model.Product;



public class ProductDAO {
	public void save(Product product) {
		Transaction transaction = null;
		try (Session session = DatabaseConfig.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public List<Product> searchProductByName(String name){
		try(Session session = DatabaseConfig.getSessionFactory().openSession()){
			Query<Product> query = session.createQuery("FROM Product WHERE name LIKE :name", Product.class)
					.setParameter("name", "%" + name + "%");
			return query.list();
		}
	}
	
	public List<Product> searchProductByCategory(String category){
		try(Session session = DatabaseConfig.getSessionFactory().openSession()){
			Query<Product> query = session.createQuery("FROM Product WHERE category = :category", Product.class)
					.setParameter("category", category);
			return query.list();
		}
	}
	
	public List<Product> searchProductsByNameAndCategory(String name, String category){
		try(Session session = DatabaseConfig.getSessionFactory().openSession()){
			Query<Product> query = session.createQuery("FROM Product WHERE name LIKE :name AND category = :category", Product.class)
					.setParameter("name", "%" + name + "%")
					.setParameter("category", category);
			return query.list();
			}
	}
	public static List<Product> getAllProducts() {
		try(Session session = DatabaseConfig.getSessionFactory().openSession()){
			return session.createQuery("FROM Product",Product.class).list();
		}
	}
}
