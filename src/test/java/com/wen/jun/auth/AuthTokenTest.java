package com.wen.jun.auth;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.wen.jun.Startup;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Startup.class)
@WebAppConfiguration
public class AuthTokenTest {
	
	@Autowired
	WebApplicationContext ctx;
	
	@Autowired
	AuthController authController;
	
	private MockMvc mockMvc;
	
	String token = "";
	
	@Before
	public void setUp(){
		System.out.println("sdffffffffffffff");
		//mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
		
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		System.out.println("11111成功");
		GenericWebApplicationContext gctx;
	}
	
	/**
	 * 注册api用户
	 * 
	 * @throws Exception
	 */
	@Test
	public void registerApiUser() throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
				.param("username", "suwenjun")
				.param("password", "123")
				.param("roles", "ROLE_ADMIN,ROLE_USER")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		
		int statusCode = result.getResponse().getStatus();
		
		Assert.assertEquals(statusCode, 200);
		
		String body = result.getResponse().getContentAsString();
		
		System.out.println("body:"+body);
				
	}
	

	
	@Test
	public void auth() throws Exception{
		registerApiUser();
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth")
				.param("username", "suwenjun")
				.param("password", "123")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		
		int statusCode = result.getResponse().getStatus();
		
		Assert.assertEquals(statusCode, 200);
		
		String body = result.getResponse().getContentAsString();
		
		
		
		
		System.out.println("body:"+body);
		
		
		JSONObject json = new JSONObject(body);
		
		System.out.println("json="+json);
		
		String token = json.getJSONObject("data").getJSONObject("token").getString("token");
		
		System.out.println("token="+token);
		
				
		this.token = token;
	}
	
	
	@Test
	public void usersList() throws Exception{
		auth();
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/list")
				.param("page", "1")
			.param("limit", "2")
				.header("Authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		
		int statusCode = result.getResponse().getStatus();
		
		System.out.println("ssd statusCode  = "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		String body = result.getResponse().getContentAsString();
		
		
		
		
		System.out.println("body:"+body);
		
		
		JSONObject json = new JSONObject(body);
		
		System.out.println("json="+json);
		
		
	}
}


















