package com.sunday.wkday.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AccessHandler {

    @Around("execution(public * com.sunday.wkday.controller.*.*(..))")
    public Object payQueryServicePoincut(ProceedingJoinPoint joinPoint) throws Throwable {
        String className =joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        try {
            Object resp = joinPoint.proceed();
            log.info("{}#{}() req:{},result:{}", className, method, joinPoint.getArgs(), resp);
            return resp;
        } catch (Throwable t) {
            log.error("{}#{}() req:{}, error:{}", className, method, joinPoint.getArgs(), t.getMessage());
            throw t;
        }
    }
}
