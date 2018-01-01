package com.wen.jun.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wen.jun.auth.secruity.JwtTokenUtil;
import com.wen.jun.auth.secruity.JwtUser;
import com.wen.jun.auth.user.User;
import com.wen.jun.auth.user.UserService;
import com.wen.jun.rest.cfg.business.AuthConfig;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.Arrays.asList;

@Service
public class AuthServiceImpl implements AuthService {

	
	@Autowired
	AuthConfig authConfig;
	
	@Autowired
	UserService userRepository;
	
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    
    
    
    

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public User register(User userToAdd) {
        final String username = userToAdd.getUsername();
        
        User u = userRepository.findByUsername(username);
        
        
        if(u!=null) {
            return u;
        }
        
        
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
        if(userToAdd.getRoles() == null || userToAdd.getRoles().size() < 1)userToAdd.setRoles(asList("ROLE_USER"));
        	
        userRepository.insert(username,userToAdd);
        return userToAdd;
        //return userRepository.insert(userToAdd);
    }


	@Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(authConfig.getAuthPrefix().length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        
        System.out.println("刷新token，username="+username);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
    
    
}
