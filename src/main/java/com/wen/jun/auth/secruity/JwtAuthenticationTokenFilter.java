package com.wen.jun.auth.secruity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wen.jun.rest.cfg.business.AuthConfig;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.wen.jun.common.WebConsts.DEFAULT_FIRST_PAGE_NO;
import static com.wen.jun.common.WebConsts.DEFAULT_PAGE_SIZE;

import java.io.IOException;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	AuthConfig authConfig;
	
	
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(authConfig.getAuthHeaderName());
        
        
        
        if (authHeader != null && authHeader.startsWith(authConfig.getAuthPrefix())) {
            final String authToken = authHeader.substring(authConfig.getAuthPrefix().length()); // The part after "Bearer "
           
            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            //assemblePageBounds(request);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 如果我们足够相信token中的数据，也就是我们足够相信签名token的secret的机制足够好
                // 这种情况下，我们可以不用再查询数据库，而直接采用token中的数据
                // 本例中，我们还是通过Spring Security的 @UserDetailsService 进行了数据查询
                // 但简单验证的话，你可以采用直接验证token是否合法来避免昂贵的数据查询
            	
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                System.out.println("userDetails="+userDetails);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(   request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    
                }
            }
        }

        chain.doFilter(request, response);
    }
    
  
}
