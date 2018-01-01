package com.wen.jun.rest.cfg.db;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


@Configuration
public class MongoDBConfiguration {
	
	@Autowired
	MongoClient mongoClient;
	

	@Bean(name="logs")
	public MongoDatabase logs(){
		
		MongoDatabase database = mongoClient.getDatabase("logs");  
		return database;
	}
	
	
	@Bean(name="logs.sms_send")
	public MongoCollection <Document> mc_user_feature(){
		
		MongoCollection<Document> mc = logs().getCollection("sms_send");  
		
		return mc;
	}
	
	
	
	@Bean(name="logs.email_send")
	public MongoCollection <Document> email_send(){
		MongoCollection<Document> mc = logs().getCollection("email_send");  
		
		return mc;
	}
	
	
	@Bean(name="logs.push_send")
	public MongoCollection <Document> mc_push_send(){
		
		MongoCollection<Document> mc = logs().getCollection("push_send");  
		
		return mc;
	}

	@Bean(name="logs.msg_discard")
	public MongoCollection <Document> sms_invalid(){
		
		MongoCollection<Document> mc = logs().getCollection("msg_discard");  
		
		return mc;
	}
	
}
