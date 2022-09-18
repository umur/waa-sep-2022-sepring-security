package com.example.security.aspect;

import com.example.security.entity.FireWord;
import com.example.security.entity.FireWordLogger;
import com.example.security.entity.User;
import com.example.security.repository.FireWordLoggerRepository;
import com.example.security.repository.UserRepository;
import com.example.security.security.JwtUtil;
import com.example.security.service.impl.FireWordLoggerServiceImpl;
import com.example.security.service.FireWordService;
import com.example.security.service.impl.FireWordServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class WaaOffensiveWords {

    @Autowired
    FireWordServiceImpl fireWordService;

    @Autowired
    FireWordLoggerServiceImpl fireWordLoggerService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    FireWordLoggerRepository fireWordLoggerRepository;

    @Pointcut("@annotation(com.example.security.aspect.Loggable)")
    public void dummyMethod(){

    }

    @Around("dummyMethod()")
    public Object offensiveWordsAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        boolean offensive = fireWordService.checkOffensiveWord(Arrays.stream(joinPoint.getArgs()).findFirst().get().toString());
            //System.out.println(Arrays.stream(joinPoint.getArgs()).findFirst().get().toString());
        if(offensive){
            //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String token = httpServletRequest.getHeader("Authorization").substring(7);
            User user = userRepository.findByEmail(jwtUtil.getUsernameFromToken(token)).get();
            List<FireWordLogger> blacklist = fireWordLoggerService.getListOfUser(user.getId()).stream().filter(fireWordLogger -> ChronoUnit.MINUTES.between(LocalDateTime.now(),fireWordLogger.getTime())<30).collect(Collectors.toList());
            FireWord fireWord = new FireWord("stupid");
            fireWordLoggerRepository.save(new FireWordLogger(user.getId(),LocalDateTime.now(),fireWord));
            if(blacklist.size() > 5)
                    throw new RuntimeException("Max number of fire words with last 30 minutes");

        }
        Object result = joinPoint.proceed();
        return result;
    }
}
