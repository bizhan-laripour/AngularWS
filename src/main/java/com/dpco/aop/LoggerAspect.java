package com.dpco.aop;

import com.dpco.exception.CustomException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


@Aspect
@Configuration
public class LoggerAspect {


    @Pointcut("@annotation(com.dpco.aop.LoggAnnotation) && @annotation(requestMapping)")
    public void controller(RequestMapping requestMapping){

    }

    @Around("controller(requestMapping)")
    public Object around(ProceedingJoinPoint joinPoint , RequestMapping requestMapping) throws Throwable {
        Object[] obj = joinPoint.getArgs();
        File file = new File("log.txt");
        Object object = null;
        try{
            if(file.createNewFile()){
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("\n\n-----------------------------------------this is the logger of project , you can see the methods that calls and information of them  in here------------------------------------\n\n");
                fileWriter.close();
            }
            FileWriter fileWriter = new FileWriter(file , true);
            Date first = new Date();
            fileWriter.write("\n---------------------------------------"+joinPoint.getSignature().getName()+"-------------------------------------------\n");
            fileWriter.write("path is : "+requestMapping.path()[0]+"\n");
            fileWriter.write("the method of request is :"+ requestMapping.method()[0]+"\n");
            StringBuilder builder = new StringBuilder();
            for(Object arg : obj) {
                builder.append(arg.toString() + ",");
            }
            builder.deleteCharAt(builder.indexOf(builder.toString()));
            fileWriter.write("the arguments of this method is : "+builder.toString()+"\n");
            object = joinPoint.proceed();
            Date second = new Date() ;
            fileWriter.write("the milli seconds that left : "+String.valueOf(second.getTime() - first.getTime()));
            fileWriter.close();
        } catch (IOException e) {
            throw new CustomException("hello" , HttpStatus.MULTI_STATUS);
        }
        return object;
    }
}
