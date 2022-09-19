package miu.edu.lab3.Aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
    @Component
    public class LoggerAspect {

        @Pointcut("@annotation(miu.edu.lab3.Aspect.Annotation.LogMe)")
        public void a(){}

        @Before("a()")
        public void log(JoinPoint joinPoint){
            System.out.println("Method Name: " + joinPoint.getSignature().getName());
        }
    }


