package com.dpco.controller;


import com.dpco.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class HelloController {

    @RequestMapping(path = "/hello" , method = RequestMethod.GET)
    public String hello() {
        try {
            return "Hello World";
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }
    }

}
