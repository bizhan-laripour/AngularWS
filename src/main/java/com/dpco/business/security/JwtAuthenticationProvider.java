package com.dpco.business.security;

import com.dpco.business.dto.LoginDto;
import com.dpco.business.exception.CustomException;
import com.dpco.logger.Logger4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator validator;

    @Autowired
    private Logger4j logger4j;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        try {
            JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
            String token = jwtAuthenticationToken.getToken();

            LoginDto jwtUser = validator.validate(token);

            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(jwtUser.getRole());
            return new JwtUserDetails(jwtUser.getUsername(), jwtUser.getId(),
                    token,
                    grantedAuthorities);
        }catch (Exception ex){
            logger4j.getLogger(ex);
            throw new CustomException(ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        try {
            return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
        }catch (Exception ex){
            logger4j.getLogger(ex);
            throw new CustomException(ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
