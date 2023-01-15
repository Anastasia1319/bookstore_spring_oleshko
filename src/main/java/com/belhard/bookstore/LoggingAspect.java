package com.belhard.bookstore;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("@annotation(Logging)")
    public void adviceBefore(JoinPoint joinPoint) {
        System.out.println("Arguments before calling a method" + joinPoint.getArgs());
    }

    @After("@annotation(Logging)")
    public void adviceAfter (JoinPoint joinPoint) {
        System.out.println("Arguments after calling a method" + joinPoint.getArgs());
    }
}
