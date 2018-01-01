package com.wen.jun.rest.cfg.business;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="auth")
//@Data
public class AuthConfig {
	
	private Long expiration;

	
	private String secret;
	
	private String authHeaderName;
	
	private String authPrefix;
	
	
	
	
	
	
	
	

	public String getAuthPrefix() {
		return authPrefix+" ";
	}

	public void setAuthPrefix(String authPrefix) {
		this.authPrefix = authPrefix;
	}

	public String getAuthHeaderName() {
		return authHeaderName;
	}

	public void setAuthHeaderName(String authHeaderName) {
		this.authHeaderName = authHeaderName;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	
	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
