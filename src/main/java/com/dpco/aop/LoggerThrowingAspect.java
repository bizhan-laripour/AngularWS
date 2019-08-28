package com.dpco.aop;

import com.dpco.business.exception.CustomException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Aspect
@Configuration
public class LoggerThrowingAspect {


    @AfterThrowing(pointcut = "execution(* com.dpco.business.service.*.*(..))" , throwing = "exeption")
    public void afterTrowing(JoinPoint joinPoint , Throwable exeption) throws IOException {
        try {
            File file = new File("log.txt");
            if (file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("\n\n-----------------------------------------this is the logger of project , you can see the methods that calls and information of them  in here------------------------------------\n\n");
                fileWriter.close();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\n\n-----------------------------------------------------------------------------------\n\n");
            Date now = new Date();
            fileWriter.write(now.toString() + "\n");
            fileWriter.write("exception message :" + exeption.getMessage() + "\n");
//        fileWriter.write("status code is :"+exeption.getStatus().value()+"\n");
            StackTraceElement stackTraceElement = exeption.getStackTrace()[0];
            fileWriter.write("the class that exception happened: " + stackTraceElement.getClassName() + "\n");
            fileWriter.write("the method that exception happened: " + stackTraceElement.getMethodName() + "\n");
            fileWriter.write("the line that exception happend: " + stackTraceElement.getLineNumber() + "\n");
            fileWriter.write("-----------------------------------------------------------------------------------------");
            fileWriter.close();
        }catch (CustomException ex){
            throw new CustomException("some error in aop", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @AfterThrowing(pointcut = "execution(* com.dpco.business.dao.*.*(..))" , throwing = "exeption")
    public void afterTrowingDao(JoinPoint joinPoint , Throwable exeption) throws IOException {
        try {
            File file = new File("log.txt");
            if (file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("\n\n-----------------------------------------this is the logger of project , you can see the methods that calls and information of them  in here------------------------------------\n\n");
                fileWriter.close();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\n\n-----------------------------------------------------------------------------------\n\n");
            Date now = new Date();
            fileWriter.write(now.toString() + "\n");
            fileWriter.write(" exception message :" + exeption.getMessage() + "\n");
            StackTraceElement stackTraceElement = exeption.getStackTrace()[0];
            fileWriter.write("the class that exception happened: " + stackTraceElement.getClassName() + "\n");
            fileWriter.write("the method that exception happened: " + stackTraceElement.getMethodName() + "\n");
            fileWriter.write("the line that exception happend: " + stackTraceElement.getLineNumber() + "\n");
            fileWriter.write("-----------------------------------------------------------------------------------------");
            fileWriter.close();
        }catch (CustomException ex){
            throw new CustomException("some error in aop", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
