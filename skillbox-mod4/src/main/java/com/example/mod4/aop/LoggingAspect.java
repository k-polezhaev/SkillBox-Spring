package com.example.mod4.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("@annotation(Loggable)")
    public void logBefore(JoinPoint joinPoint){
        log.info("---Before execute of: {}", joinPoint.getSignature().getName());
    }
    @After("@annotation(Loggable)")
    public void logAfter(JoinPoint joinPoint){
        log.info("---After execute of: {}", joinPoint.getSignature().getName());
    }
}
