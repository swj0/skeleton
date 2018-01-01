package com.wen.jun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;



public class War extends SpringBootServletInitializer{//支持打成war包,注意pom的修改
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(Startup.class);
	} 
	
	
}
