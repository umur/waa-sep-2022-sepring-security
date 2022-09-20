package edu.miu.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
//    @Pointcut("execution(edu.miu.lab_4.controller.ProductController.*(..))")
//    public void logMe(){
//
//    }

    /*@Before("logMe()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("Lof Before the methodz: " + joinPoint.getSignature().getName());
    }
    @After("logMe()")
    public void logAfter(JoinPoint joinPoint){
        System.out.println("Lof Before the methodz: " + joinPoint.getSignature().getName());
    }
    @Pointcut("@annotation(edu.miu.lab_4.aspect.annotation.LogMe)")
    public void logMeAnnotation() {

    }*/

}
