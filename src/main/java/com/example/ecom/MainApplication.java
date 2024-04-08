package com.example.ecom;

import org.glassfish.jersey.server.ResourceConfig;

public class MainApplication extends ResourceConfig {
	public MainApplication() {
		packages("com.example.ecom.resources");
	}
}
