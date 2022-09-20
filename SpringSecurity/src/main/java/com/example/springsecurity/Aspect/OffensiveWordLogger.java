package com.example.springsecurity.Aspect;

import com.example.springsecurity.Aspect.Model.OffensiveWordLog;
import com.example.springsecurity.Aspect.Repository.OffensiveWordLogRepo;
import com.example.springsecurity.Model.AppUser;
import com.example.springsecurity.Model.OffensiveWord;
import com.example.springsecurity.Repository.AppUserRepo;
import com.example.springsecurity.Repository.OffensiveWordRepo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
public class OffensiveWordLogger {
    private OffensiveWordRepo offensiveWordRepo;
    private AppUserRepo appUserRepo;
    private OffensiveWordLogRepo offensiveWordLogRepo;

    public OffensiveWordLogger(OffensiveWordLogRepo offensiveWordLogRepo, OffensiveWordRepo offensiveWordRepo, AppUserRepo appUserRepo){
        this.offensiveWordLogRepo=offensiveWordLogRepo;
        this.offensiveWordRepo=offensiveWordRepo;
        this.appUserRepo=appUserRepo;
    }

    @Pointcut("within(com.example.springsecurity.Security.Service.UaaService)")
    public void uaaservicepointcut(){}
    @Pointcut("within(com.example.springsecurity.Service.*.*)")
    public void appservicepointcut(){}

    @Around("uaaservicepointcut() && appservicepointcut()")
    public Object offensivewordlogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        List<OffensiveWord> offensiveWords = (List) offensiveWordRepo.findAll();
        List<String> words =offensiveWords.stream()
                .map(offensiveWord -> offensiveWord.getWord())
                .collect(Collectors.toList());
         List<String> arg=Arrays.stream(args).map(Object::toString).collect(Collectors.toList());
         List<String> wordsfound = arg.stream().filter(a->words.contains(a)).collect(Collectors.toList());

         if(wordsfound.size()==0){
             Object result = proceedingJoinPoint.proceed();
             return result;
         }
         else{
             UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
             AppUser user = appUserRepo.findAppUserByUsername(userDetails.getUsername());

             for(String off: wordsfound){
                 OffensiveWordLog offense = new OffensiveWordLog();
                 offense.setWord(off);
                 offense.setAppuserid(user.getId());
                 offense.setTimestamp(LocalDateTime.now());
                 offensiveWordLogRepo.save(offense);
             }
             List<OffensiveWordLog> offenslogs=offensiveWordLogRepo.findOffensiveWordLoggerByAppuseridAndTimestampMinuteBefore(user.getId(),30);

             if(offenslogs.size()>5){
                 System.out.println("Maximum number of offensive word in 30 mins");
             }
             else{
                 Object result =proceedingJoinPoint.proceed();
                 return result;
             }

         }
         return null;
    }


}
