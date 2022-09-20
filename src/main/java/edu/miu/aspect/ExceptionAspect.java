///*
//package edu.miu.aspect;
//
////import edu.miu.exception.AopIsAwesomeHeaderException;
//import edu.miu.exception.AopIsAwesomeHeaderException;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//public class ExceptionAspect {
//
//    @Autowired
//    private HttpServletRequest request;
//
//    @Around("within(edu.miu.controller.*)")
//    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("hi");
//        if (request.getMethod().equals("POST")) {
//            var header = request.getHeader("AOP-IS-AWESOME");
//            if (header != null) {
//                return joinPoint.proceed();
//            } else {
//                throw new AopIsAwesomeHeaderException(joinPoint.getSignature().getName()+ " is missing AOP-IS-AWESOME header.");
//            }
//        } else {
//            return joinPoint.proceed();
//        }
//
//    }
//
//
//
//
//}
//*/
