package com.dpco.logger;

import com.dpco.business.exception.CustomException;
import com.dpco.controller.LoginController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.springframework.stereotype.Component;

@Component
public class Logger4j {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    public Logger getLogger(CustomException ex){
        logger.error("--------------------------------------------------------------------------------------------");
        logger.error("the exception is :"+ex.getMessage());
        logger.error("the class is :"+ex.getStackTrace()[0].getClassName());
        logger.error("the method is :"+ex.getStackTrace()[0].getMethodName());
        logger.error("the line number is :"+ex.getStackTrace()[0].getLineNumber());
        return logger;
    }
}
