package com.dpco.logger;

import com.dpco.business.exception.CustomException;
import com.dpco.controller.LoginController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Logger4j {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    public Logger getLogger(Throwable ex){
        List<String> list = exceptionParser(ex);
        logger.error("--------------------------------------------------------------------------------------------");
        logger.error("the exception is :"+ex.getMessage());
        logger.error("lines that exception occurred : \n" );
        for(String st : list){
            logger.error(st);
        }
        return logger;
    }


    public List<String> exceptionParser(Throwable ex){
        List<String> list = new ArrayList<>();
        String[] st = Arrays.toString(ex.getStackTrace()).split(",");
        for(String obj: st){
            if(obj.contains("com")){
                list.add(obj);
            }
        }
        return list;
    }
}
