package com.wen.jun.rest.domain;

public class UserBase {
	
	private String username;
	
	private int age;
	
	private String id;
	private String sex;//实际常用枚举

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	

}
