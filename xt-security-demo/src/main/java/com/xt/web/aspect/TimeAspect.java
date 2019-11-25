package com.xt.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class TimeAspect {

    @Pointcut("execution(* com.xt.web.controller.UserController.*(..))")
    public void declareJoinPointExpression(){}

    @Around("declareJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint pjp) {
        System.out.println("time aspect start");
        long start = new Date().getTime();
        Object value = null;
        try {
            value = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("method：" + pjp.getSignature().getName());
        System.out.println("args: " + Arrays.asList(pjp.getArgs()));
        System.out.println("time aspect 耗时：" + (new Date().getTime() - start));
        System.out.println("time aspect end");
        return value;
    }
}
