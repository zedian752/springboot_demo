package org.spring_demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class allController {

    @Pointcut("@annotation(org.spring_demo.customizeAnnotation.whiteLIst)")
    public void createUserNamePointCut() {
    }
    @Around("createUserNamePointCut() && args(name)")
//    @Around("createUserNamePointCut()")
    public Object doAround(ProceedingJoinPoint pjp, String name ) throws Throwable {
       return pjp.proceed();
    }

}
