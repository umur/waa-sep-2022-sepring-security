package edu.miu.lab5springsecurity.security;


import edu.miu.lab6springsecurity.dto.ReviewDto;
import edu.miu.lab6springsecurity.entity.redis.VulgarityUsage;
import edu.miu.lab6springsecurity.repository.UserRepo;
import edu.miu.lab6springsecurity.repository.VulgarityUsageRepo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class WaaOffensiveWords {
    private final String offensiveWord = "spring";

    @Autowired
    private VulgarityUsageRepo vulgarityUsageRepo;

    @Autowired
    private UserRepo userRepo;

    @Pointcut("within(edu.miu.lab6springsecurity.controller..*) && args(.., reviewDto)")
    public void reviewRequests(@RequestBody ReviewDto reviewDto){
    }

    @Around("reviewRequests(reviewDto)")
    public Object checkVulgarity(ProceedingJoinPoint joinPoint, ReviewDto reviewDto) throws Throwable{
        var comment = reviewDto.getComment();
        if(comment.contains(offensiveWord)){
            reviewDto.setComment(comment.replace(offensiveWord, "*".repeat(offensiveWord.length())));

            log.info("Creating vulgarity usage");
            VulgarityUsage vulgarityUsage = new VulgarityUsage();
            vulgarityUsage.setWord(offensiveWord);
            vulgarityUsage.setUsedAt(new Date());
            var email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            var user = userRepo.findByEmail(email);
            vulgarityUsage.setUserId(user.getId());
            vulgarityUsageRepo.save(vulgarityUsage);
        }
        return joinPoint.proceed();
    }
}
