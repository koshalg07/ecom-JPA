package com.example.ecom.dao;

import org.hibernate.Session;

import org.hibernate.Transaction;

import com.example.ecom.config.DatabaseConfig;
import com.example.ecom.model.User;



public class UserDAO {
	public void save(User user) {
		Transaction transaction = null;
		try(Session session = DatabaseConfig.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public User getUserByUsername(String username) {
		try(Session session = DatabaseConfig.getSessionFactory().openSession()){
			return session.createQuery("FROM User WHERE username = :username", User.class)
					.setParameter("username",username)
					.uniqueResult();
		}
	}
	
	public void update(User user) {
		Transaction transaction = null;
		try(Session session = DatabaseConfig.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
		} catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
				
			}
			
			e.printStackTrace();
		}
	}
}
