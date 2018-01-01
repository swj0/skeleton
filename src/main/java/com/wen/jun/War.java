package com.wen.jun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;



public class War extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(Startup.class);
	} 
	
	
}
