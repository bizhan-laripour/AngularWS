package com.dpco.controller;

import com.dpco.business.dto.LoginDto;
import com.dpco.business.entity.Member;
import com.dpco.business.exception.CustomException;
import com.dpco.business.exception.ResultBody;
import com.dpco.business.security.JwtGenerator;
import com.dpco.business.service.Exp;

import com.dpco.business.service.MemberService;
import com.dpco.logger.Logger4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:4200"})
public class LoginController {


    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private MemberService memberService;
    @Autowired
    private Exp exp;


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResultBody generate(@RequestBody LoginDto loginDto) throws Exception {
        try {
            return new ResultBody(jwtGenerator.generate(loginDto), HttpStatus.OK.value());
        } catch (CustomException ex) {
            throw new CustomException("some thing wrong in login", ex.getStatus());
        }

    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Member save(@RequestBody Member member) {
        try {
            return memberService.save(member);
        }catch (CustomException ex){
            throw new CustomException("some thing wrong in saving" , ex.getStatus());
        }
    }


    @RequestMapping(path = "/ex", method = RequestMethod.GET)
    public String sayMe() {
        try {
            return exp.go();
        } catch (CustomException ex) {
            throw new CustomException("some thing wrong in ex", ex.getStatus());

        }
    }
}
