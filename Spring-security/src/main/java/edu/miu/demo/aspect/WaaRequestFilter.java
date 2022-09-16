package edu.miu.demo.aspect;

import edu.miu.demo.model.Counter;
import edu.miu.demo.repo.CounterRepo;
import edu.miu.demo.security.WaaUserDetails;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class WaaRequestFilter {
    private final CounterRepo repository;

    @Pointcut("within(edu.miu.demo.controller..*)")

    public void annotated() {}
    @Before("annotated()")
    public void checkActivity(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().toString().equals("anonymousUser")) {
            WaaUserDetails userDetails = (WaaUserDetails) authentication.getPrincipal();
            Instant now = Instant.now();
            Instant thirtyMinEarlier = now.minus(30, ChronoUnit.MINUTES);
            List<Counter> activities = repository.findAllByUserIdAndCreatedAtBetween(userDetails.getId(), thirtyMinEarlier, now);
            if (activities.size() > 5) {
                Instant lastActivityTime = activities.get(4).getCreatedAt();
                if (lastActivityTime.plus(15, ChronoUnit.MINUTES).isAfter(now))  {
                    long waitMinutes = (lastActivityTime.plus(15, ChronoUnit.MINUTES).getEpochSecond() - now.getEpochSecond()) / 60;
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Max Bad Words Requests Limit has been Reached. You need wait for %o minutes.", waitMinutes));
                }
            }
        }
    }
}
