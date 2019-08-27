package com.dpco.security;

import com.dpco.dto.LoginDto;
import com.dpco.entity.Member;
import com.dpco.exception.CustomException;
import com.dpco.service.MemberService;
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

    @Value("${security.jwt.token.expire-length}") // 1 hour
    private long validityInMilliseconds;

    public String generate(LoginDto loginDto){
        try {
            Member member = new Member();
            member.setUsername(loginDto.getUsername());
            member.setPassword(loginDto.getPassword());
            if (memberService.findByUsernameAndPassword(member) != null) {
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
                throw new CustomException("there is no member with this username and password so it is forbidden", HttpStatus.FORBIDDEN);
            }
        }catch (CustomException ex){
            throw new CustomException("some exception in building the jwt token" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
