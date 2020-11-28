package ru.springcourse.lessons.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
    private static final Logger logger =
            LoggerFactory.getLogger(CRMLoggingAspect.class);

    @Pointcut("execution(* ru.springcourse.lessons.controllers.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* ru.springcourse.lessons.service.api.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* ru.springcourse.lessons.dao.api.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        logger.info("===> in @Before: calling method " +
                joinPoint.getSignature().toShortString());
        Object[] args = joinPoint.getArgs();
        for (Object arg:args) {
            logger.info("===> argument: " + arg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        logger.info("===> in @AfterReturning: from method " +
                joinPoint.getSignature().toShortString());
        logger.info("===> result: " + result);
    }
}
