package com.wen.jun.auth.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "user", description = "api使用账号用户对象")
public class User {
    private String id;

    @ApiModelProperty(value = "账号",required=true)
    private String username;

    @ApiModelProperty(value = "密码",required=true)
    private String password;
    private String email;
    private Date lastPasswordResetDate;
    private List<String> roles;
    
    
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}
	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", lastPasswordResetDate=" + lastPasswordResetDate + ", roles=" + roles + "]";
	}
    
    
    
}
