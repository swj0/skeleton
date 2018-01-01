package com.wen.jun.rest.mock;


import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class SimpleTest {

	@Test
	public void simpleTest(){
		//
		List<String> list  = mock(List.class);
		
		//
		when(list.get(0)).thenReturn("helloworld");
		
		String result = list.get(0);
		
		
		verify(list).get(0);
		
		//jnunit
		Assert.assertEquals("helloworld", result);
	}
}

















