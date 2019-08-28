package com.dpco.business.dao;

import com.dpco.business.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExpDao{

    public String hey(){
        throw new CustomException("this is dao exception" , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
