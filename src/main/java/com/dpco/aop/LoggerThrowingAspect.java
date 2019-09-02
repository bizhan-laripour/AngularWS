package com.dpco.aop;

import com.dpco.business.exception.CustomException;
import com.dpco.logger.Logger4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Aspect
@Configuration
public class LoggerThrowingAspect {

    @Autowired
    private Logger4j logger4j;


    @AfterThrowing(pointcut = "execution(* com.dpco.business.dao.*.*(..))" , throwing = "exeption")
    public void afterTrowing(JoinPoint joinPoint , Throwable exeption) throws IOException {
        try {

          List<String> list = logger4j.exceptionParser(exeption);
            File file = new File("log.txt");
            if (file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("\n\n-----------------------------------------this is the logger of project , you can see the methods that calls and information of them  in here------------------------------------\n\n");
                fileWriter.close();
            }
            FileWriter fileWriter = new FileWriter(file, true);
//            fileWriter.write("\n\n-----------------------------------------------------------------------------------\n\n");
            fileWriter.write("  EXCEPTION    \n\n");
            Date now = new Date();
            fileWriter.write(now.toString() + "\n");
            fileWriter.write("exception message :" + exeption.getMessage() + "\n");
            fileWriter.write("lines that exception occurred ===>\n");
            for(String err : list){
                fileWriter.write(err + "\n");
            }
            fileWriter.write("-----------------------------------------------------------------------------------------\n");
            fileWriter.close();
            logger4j.getLogger(exeption);
        }catch (Exception ex){
            logger4j.getLogger(ex);
            throw new CustomException("some error in aop", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//
//    @AfterThrowing(pointcut = "execution(* com.dpco.business.dao.*.*(..))" , throwing = "exeption")
//    public void afterTrowingDao(JoinPoint joinPoint , Throwable exeption) throws IOException {
//        try {
//            List<String> list = logger4j.exceptionParser(exeption);
//            File file = new File("log.txt");
//            if (file.createNewFile()) {
//                FileWriter fileWriter = new FileWriter(file);
//                fileWriter.write("\n\n-----------------------------------------this is the logger of project , you can see the methods that calls and information of them  in here------------------------------------\n\n");
//                fileWriter.close();
//            }
//            FileWriter fileWriter = new FileWriter(file, true);
////            fileWriter.write("\n\n-----------------------------------------------------------------------------------\n\n");
//            Date now = new Date();
//            fileWriter.write(now.toString() + "\n");
//            fileWriter.write(" exception message :" + exeption.getMessage() + "\n");
//            fileWriter.write("lines that exception occurred ===>\n");
//            for(String err : list){
//                fileWriter.write(err + "\n");
//            }
//            fileWriter.write("-----------------------------------------------------------------------------------------");
//            fileWriter.close();
//            logger4j.getLogger(exeption);
//        }catch (Exception ex){
//            logger4j.getLogger(ex);
//            throw new CustomException("some error in aop", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
