package com.aop.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ValidAdvice {

    // 定義切點，這裡的切點表示式指定攔截 MyService 介面中的所有方法
    @Pointcut("execution(* com.controller.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Before method execution...");
        System.out.println(getMethodName(joinPoint));
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
    }

//    @After("pointcut()")
//    public void myAfterAdvice(JoinPoint joinPoint) {
//        System.out.println("After method execution...");
//    }

    private String getMethodName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getName();
    }
}
