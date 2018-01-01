package com.wen.jun.auth.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;

@Service
public class UserService {
	
	private ConcurrentMap< String, User> users = new ConcurrentHashMap<>();
	
	public User findByUsername(String username) {
		if(username == null ){
			System.out.println("username=null");
			return null;
		}
    	User user = this.users.get(username);
    	System.out.println("找到用户.....user="+user);
    	return user;
	}
	
	
	public  User insert(String username,User user){
    	
    	System.out.println("注册用户 user="+user);
    	this.users.put(username, user);
    	
    	return user;
    }


	public List<User> findAll() {
		List<User> userAll = new ArrayList<>();
		if(users.size()> 0){
			for(User u : this.users.values()){
				userAll.add(u);
			}
		}
		return userAll;
	}
}
