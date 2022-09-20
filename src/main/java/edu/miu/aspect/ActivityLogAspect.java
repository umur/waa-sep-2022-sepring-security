package edu.miu.aspect;

import edu.miu.entity.ActivityLog;
import edu.miu.service.imp.ActivityLogServiceImp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Aspect
@Component
public class ActivityLogAspect {
    @Autowired
    private ActivityLogServiceImp activityLogService;

    @Pointcut("@annotation(edu.miu.aspect.annotation.ExecutionTime)")
    public void selectMethods() {

    }

    @Around("selectMethods()")
    public ActivityLog calculateTimeTaken(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        joinPoint.proceed();
        long end = System.currentTimeMillis();

        long timeTaken = end - start;
        ActivityLog a1 = new ActivityLog();
        a1.setDate(LocalDate.now());
        a1.setOperation("print nonsense one ");
        a1.setDuration(timeTaken);
        activityLogService.saveActivityLog(a1);
        return a1;

    }
}
