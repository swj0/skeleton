package com.wen.jun;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Startup {

	@SuppressWarnings({ "deprecation", "static-access" })
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(Startup.class);
		app.setWebEnvironment(true);
		Set<Object> set = new HashSet<Object>();  
		app.setSources(set);  
        app.run(args);  
       
	}
	
}
