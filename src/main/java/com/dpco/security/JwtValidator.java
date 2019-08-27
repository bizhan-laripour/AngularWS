package com.dpco.security;

import com.dpco.dto.LoginDto;
import com.dpco.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "dpco";

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
            throw new CustomException("this jwt is not valid" , HttpStatus.FORBIDDEN);
        }

        return jwtUser;
    }
}
