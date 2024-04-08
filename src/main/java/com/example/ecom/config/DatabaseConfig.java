package com.example.ecom.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConfig {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch(Throwable ex) {
			System.err.println("Initail SessionFactory creation failed" + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
}
