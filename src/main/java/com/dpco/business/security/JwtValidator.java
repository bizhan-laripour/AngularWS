package com.dpco.business.security;

import com.dpco.business.dto.LoginDto;
import com.dpco.business.exception.CustomException;
import com.dpco.logger.Logger4j;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "dpco";

    @Autowired
    private Logger4j logger4j;

    public LoginDto validate(String token) {

        LoginDto jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new LoginDto();

            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            logger4j.getLogger(e);
            throw new CustomException("this jwt is not valid" , HttpStatus.FORBIDDEN);
        }

        return jwtUser;
    }
}
