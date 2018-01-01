package com.wen.jun.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewRequestBodyAdvice;

import com.wen.jun.auth.secruity.JwtAuthenticationRequest;
import com.wen.jun.auth.secruity.JwtAuthenticationResponse;
import com.wen.jun.auth.user.User;
import com.wen.jun.common.JsonResult;
import com.wen.jun.rest.cfg.business.AuthConfig;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
	@Autowired
	AuthConfig authConfig;

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "注册api使用账号", notes = "注册")
    /*@ApiImplicitParams({@ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String"),
    	@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
    	@ApiImplicitParam(name = "roles", value = "角色", required = true, dataType = "List")})*/
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public JsonResult register(@ApiParam(value="账号",required=true) @RequestParam(required = true)String username,
    		@ApiParam(value="密码",required=true) @RequestParam(required = true)String password, 
    		@ApiParam(value="角色,每行一个",required=true) @RequestParam(required = true)ArrayList<String> roles) throws AuthenticationException{
        
    	
    	
    	
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(password);
    	user.setRoles(roles);
    	System.out.println("addedUser="+user);
    	return new JsonResult(true).addData("user", authService.register(user));
    }
    
    @ApiOperation(value = "认证授权获取token", notes = "获取token")
    //@ApiImplicitParams({@ApiImplicitParam(name = "authenticationRequest", value = "", required = true, dataType = "JwtAuthenticationRequest")})
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public JsonResult createAuthenticationToken(
    		@ApiParam(value="账号",required=true) @RequestParam(required = true)String username,
    		@ApiParam(value="密码",required=true) @RequestParam(required = true)String password
        ) throws AuthenticationException{
    	
    	
        final String token = authService.login(username, password);

        return new JsonResult(true).addData("token", new JwtAuthenticationResponse(token));
    }

    
    @ApiOperation(value = "刷新token", notes = "刷新token")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "Authorization",defaultValue="Bearer  " , value = "token", required = true, paramType = "header", dataType = "String")})
    @RequestMapping(value = "/auth/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(authConfig.getAuthHeaderName());
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

}
