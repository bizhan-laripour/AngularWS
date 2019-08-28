package com.dpco.business.security;

import com.dpco.business.exception.CustomException;
import com.dpco.logger.Logger4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private Logger4j logger4j;

    public JwtAuthenticationTokenFilter() {
        super("/member/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        try {
            String header = httpServletRequest.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) {
                throw new CustomException("JWT Token is missing or is not valid", HttpStatus.FORBIDDEN);
            }
            String authenticationToken = header.substring(6);
            JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
            return getAuthenticationManager().authenticate(token);
        }catch (Exception ex){
            logger4j.getLogger(ex);
            throw new CustomException(ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        try {
            super.successfulAuthentication(request, response, chain, authResult);
            chain.doFilter(request, response);
        }catch (Exception ex){

            throw new CustomException(ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
