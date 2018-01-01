package com.wen.jun.rest.sms.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.wen.jun.rest.dao.UserBaseMapper;
import com.wen.jun.rest.domain.UserBase;


import java.util.*;


@Service
public class UserBaseService {
	
	@Autowired
	UserBaseMapper userBaseMapper;
	
	Map<String,UserBase> users = new HashMap<>();
	
	
	
	//这里就不操作数据库了
	//使用map来存储
	@PostConstruct
	public void init(){
		for(int i=1;i<11;i++){
			UserBase user = new UserBase();
			user.setAge(i);
			user.setSex("f");
			user.setUsername("user"+i);
			users.put("user"+i, user);
		}
	}

	
	
	
	public List<UserBase> findAll(Integer page,Integer limit) {
		//不考虑排序问题
		
		
		List<UserBase> all = new  ArrayList<>(users.values());
		List<UserBase> result = new ArrayList<>();
		
		int total = all.size();
		
		int skip = (page - 1) * limit ;
		
		for(int index=skip;index<(page * limit) && index < total;index++){
			result.add(all.get(index));
		}
		
		return new PageList<>(result, new Paginator(page, limit, total));
	}




	public UserBase insert(UserBase addedUser) {
		users.put(addedUser.getUsername(), addedUser);
		
		return addedUser;
	}




	public UserBase findOne(String id) {
		return this.users.get(id);
	}
	
	
	
	

}
