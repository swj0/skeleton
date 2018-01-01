package com.wen.jun.rest.ct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wen.jun.common.JsonResult;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class HelloController {

	// 根目录映射 Get访问方式 直接返回一个字符串
	@RequestMapping("/")
	Map<String, String> hello() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("content", "hello freewolf~");
        return map;
	}
	
	

	// 路由映射到/users
	@RequestMapping(value = "/users", produces="application/json;charset=UTF-8")
	public JsonResult usersList() {

        ArrayList<String> users =  new ArrayList<String>(){{
            add("freewolf");
            add("tom");
            add("jerry");
        }};

		return new JsonResult(true).addData("users", users);
	}

    @RequestMapping(value = "/hello", produces="application/json;charset=UTF-8")
    public JsonResult hello2() {
        List<String> users =  new ArrayList<String>(){{ add("hello"); }};
        return new JsonResult(true).addData("users", users);
    }

    @RequestMapping(value = "/world", produces="application/json;charset=UTF-8")
    public JsonResult world() {
        ArrayList<String> users =  new ArrayList<String>(){{ add("world"); }};
        return new JsonResult(true).addData("users", users);
    }
}

