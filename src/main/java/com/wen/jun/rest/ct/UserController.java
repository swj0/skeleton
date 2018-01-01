package com.wen.jun.rest.ct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wen.jun.common.JsonResult;
import com.wen.jun.rest.cfg.web.RequestAttribute;
import com.wen.jun.rest.domain.UserBase;
import com.wen.jun.rest.sms.service.UserBaseService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import static com.wen.jun.common.WebConsts.PGK;


/**
 * 在 @PreAuthorize 中我们可以利用内建的 SPEL 表达式：比如 'hasRole()' 来决定哪些用户有权访问。
 * 需注意的一点是 hasRole 表达式认为每个角色名字前都有一个前缀 'ROLE_'。所以这里的 'ADMIN' 其实在
 * 数据库中存储的是 'ROLE_ADMIN' 。这个 @PreAuthorize 可以修饰Controller也可修饰Controller中的方法。
 **/
@RestController
@RequestMapping("/users")
public class UserController {
	
	
    @Autowired
    private UserBaseService repository;

    
    @ApiOperation(value = "获取用户列表", notes = "用户列表")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "Authorization", value = "token",defaultValue="Bearer  " , required = true, paramType = "header", dataType = "String")})
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public JsonResult getUsers(@ApiParam(value="分页",required=false) @RequestParam(required = false)Integer page,
    		@ApiParam(value="分页大小",required=false) @RequestParam(required = false)Integer limit,@RequestAttribute(PGK)PageBounds pp) {
        
    	System.out.println("pp="+pp);
    	
    	if(page == null || page < 1)page = 1;
    	if(limit == null || limit < 1)limit = 15;
    	
    	
    	
    	return new JsonResult(true).addData("users", repository.findAll(page,limit));
    }
    
    
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public UserBase addUser(@RequestBody UserBase addedUser) {
        return repository.insert(addedUser);
    }

    
    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserBase getUser(@PathVariable String id) {
        return repository.findOne(id);
    }*/

}
