package com.dpco.aop;

import com.dpco.exception.CustomException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
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


    @Pointcut("execution(* *(..)) && @annotation(requestMapping)")
    public void controller(RequestMapping requestMapping){
    }

    @Pointcut("execution(* *(..))")
    public void exception(){

    }

    @Around("controller(requestMapping)")
    public Object around(ProceedingJoinPoint joinPoint , RequestMapping requestMapping) throws Throwable {
        try {
            Object[] obj = joinPoint.getArgs();
            File file = new File("log.txt");
            Object object = null;
            try {
                if (file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write("\n\n-----------------------------------------this is the logger of project , you can see the methods that calls and information of them  in here------------------------------------\n\n");
                    fileWriter.close();
                }
                FileWriter fileWriter = new FileWriter(file, true);
                Date first = new Date();
                fileWriter.write("\n---------------------------------------" + joinPoint.getSignature().getName() + "-------------------------------------------\n");
                fileWriter.write("path is : " + requestMapping.path()[0] + "\n");
                fileWriter.write("the method of request is :" + requestMapping.method()[0] + "\n");
                StringBuilder builder = new StringBuilder();
                for (Object arg : obj) {
                    builder.append(arg.toString() + ",");
                }
                if(builder.length() != 0) {
                    builder.deleteCharAt(builder.indexOf(builder.toString()));
                }
                fileWriter.write("the arguments of this method is : " + builder.toString() + "\n");
                object = joinPoint.proceed();
                Date second = new Date();
                fileWriter.write("the milli seconds that left : " + String.valueOf(second.getTime() - first.getTime()));
                fileWriter.close();
            } catch (IOException e) {
                throw new CustomException("hello", HttpStatus.MULTI_STATUS);
            }
            return object;
        }catch (CustomException ex){
            throw new CustomException("error in writing the log" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//
//    @AfterThrowing(pointcut = "exception()" , throwing = "exeption")
//    public void afterTrowing(JoinPoint joinPoint , CustomException exeption) throws IOException {
//        File file = new File("log.txt");
//        if(file.createNewFile()){
//            FileWriter fileWriter = new FileWriter(file);
//            fileWriter.write("\n\n-----------------------------------------this is the logger of project , you can see the methods that calls and information of them  in here------------------------------------\n\n");
//            fileWriter.close();
//        }
//        FileWriter fileWriter = new FileWriter(file , true);
//        fileWriter.write("message :"+exeption.getMessage()+"\n");
//        fileWriter.write("status code is :"+exeption.getStatus().value()+"\n");
//        StackTraceElement stackTraceElement = exeption.getStackTrace()[0];
//        fileWriter.write("the class that exception happened "+stackTraceElement.getClassName()+"\n");
//        fileWriter.write("the method that exception happened "+stackTraceElement.getMethodName()+"\n");
//        fileWriter.write("the line that exception happend "+stackTraceElement.getLineNumber()+"\n");
//        fileWriter.write("the cause of exception is "+exeption.getCause().toString());
//        fileWriter.write("-----------------------------------------------------------------------------------------");
//        fileWriter.close();
//    }
}
