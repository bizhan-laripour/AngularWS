package com.dpco.controller;


import com.dpco.business.exception.CustomException;
import com.dpco.business.exception.ResultBody;
import com.dpco.business.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class HelloController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public ResultBody hello() {
        try {
            return new ResultBody("hello world", HttpStatus.OK.value());
        } catch (CustomException ex) {
            throw new CustomException(ex.getMessage(), ex.getStatus());
        }
    }

    @RequestMapping(path = "/findAll", method = RequestMethod.POST)
    public ResultBody findAll() {
        try {
            return new ResultBody(this.memberService.findAll() , HttpStatus.OK.value());
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }
    }

}
