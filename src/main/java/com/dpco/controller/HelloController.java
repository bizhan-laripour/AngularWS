package com.dpco.controller;


import com.dpco.business.exception.CustomException;
import com.dpco.business.exception.ResultBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = {"http://localhost:4200"})
public class HelloController {



    @RequestMapping(path = "/hello" , method = RequestMethod.GET)
    public ResultBody hello() {
        try {
            return new ResultBody("hello world" , HttpStatus.OK.value());
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }
    }

}
