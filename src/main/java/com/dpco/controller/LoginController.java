package com.dpco.controller;

import com.dpco.dto.LoginDto;
import com.dpco.entity.Member;
import com.dpco.exception.CustomException;
import com.dpco.security.JwtGenerator;
import com.dpco.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {


    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private MemberService memberService;


    @RequestMapping(path = "/login" , method = RequestMethod.POST)
    public String generate(@RequestBody LoginDto loginDto) throws Exception {

        return jwtGenerator.generate(loginDto);

    }

    @RequestMapping(path = "/save" , method = RequestMethod.POST)
    public Member save(@RequestBody Member member){
        return memberService.save(member);
    }


    @RequestMapping(path = "/ex" , method = RequestMethod.GET)
    public String sayMe(){
        throw new CustomException("hello this is exception" , HttpStatus.MULTI_STATUS);
    }
}
