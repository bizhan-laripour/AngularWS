package com.dpco.business.security;

import com.dpco.business.dto.LoginDto;
import com.dpco.business.exception.CustomException;

import com.dpco.logger.Logger4j;
import com.dpco.business.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    @Autowired
    private MemberService memberService;

    @Autowired
    private Logger4j logger4j;

    @Value("${security.jwt.token.expire-length}") // 1 hour
    private long validityInMilliseconds;

    public String generate(LoginDto loginDto){
        try {
            if (memberService.findByUsernameAndPassword(loginDto.getUsername() , loginDto.getPassword()) != null) {
                Claims claims = Jwts.claims()
                        .setSubject(loginDto.getUsername());
                claims.put("userId", String.valueOf(loginDto.getId()));
                claims.put("role", loginDto.getRole());
                Date validity = new Date(new Date().getTime() + validityInMilliseconds);
                return Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, "dpco")
                        .setExpiration(validity)
                        .compact();
            } else {
//                throw new CustomException("there is no member with this username and password so it is forbidden", HttpStatus.FORBIDDEN);
                return null;
            }
        }catch (Exception ex){
            logger4j.getLogger(ex);
            throw new CustomException(ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
