package miu.edu.lab3.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component

public class ExecutionTImeAspect {


        @Pointcut("@annotation(miu.edu.lab3.Aspect.Annotation.ExecutionTIme)")
        public void executionTimeAnnotation() {
        }

        @Around("executionTimeAnnotation()")
        public Object calculateExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            long start = System.nanoTime();
            var result = proceedingJoinPoint.proceed();
            long finish = System.nanoTime();
            System.out.println(proceedingJoinPoint.getSignature().getName() + " takes ns: " + finish);
            return result;
        }

    }


