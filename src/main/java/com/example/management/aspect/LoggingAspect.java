package com.example.management.aspect;

import com.example.management.message.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final Producer producer;

    @Pointcut("execution( * com.example.management.service.*.*(..))")
    private void allMethodsFromServicePackage(){
    }

    @Pointcut("execution( * com.example.management.service.EmployeeService.saveEmployee(..))")
    private void saveEmployee(){

    }

    @After(value = "allMethodsFromServicePackage()")
    public void logAfter(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<String> args = Arrays.stream(joinPoint.getArgs()).map(Object::toString).toList();
        logger.info("Method: {} executed with arguments:" +
                " {}", methodName, args);
    }

    @AfterReturning(value = "saveEmployee()", returning = "retVal")
    public void sendMessage(JoinPoint joinPoint, Object retVal) throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        producer.sendMessage(objectWriter.writeValueAsString(retVal));
    }
}
