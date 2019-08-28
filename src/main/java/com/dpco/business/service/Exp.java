package com.dpco.business.service;

import com.dpco.business.dao.ExpDao;
import com.dpco.business.exception.CustomException;
import com.dpco.logger.Logger4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Exp {

    @Autowired
    private ExpDao expDao;



    public String go(){
        try{
          return  expDao.hey();
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }
    }
}
