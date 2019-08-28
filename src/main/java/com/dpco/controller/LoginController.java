package com.dpco.controller;

import com.dpco.business.dto.LoginDto;
import com.dpco.business.entity.Member;
import com.dpco.business.exception.CustomException;
import com.dpco.business.security.JwtGenerator;
import com.dpco.business.service.Exp;
import com.dpco.business.service.MemberService;
import com.dpco.logger.Logger4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {


    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private MemberService memberService;

    @Autowired
    private Exp exp;

    @Autowired
    private Logger4j logger4j;



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
        try {

          return  exp.go();
        }catch (CustomException ex){
            logger4j.getLogger(ex);
            throw new CustomException(ex.getMessage() , ex.getStatus());

        }
    }
}
