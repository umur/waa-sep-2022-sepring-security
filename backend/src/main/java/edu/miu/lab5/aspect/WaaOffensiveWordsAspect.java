package edu.miu.lab5.aspect;

import edu.miu.lab5.entity.OffensiveWordAudit;
import edu.miu.lab5.entity.RequestLimiter;
import edu.miu.lab5.entity.Review;
import edu.miu.lab5.entity.User;
import edu.miu.lab5.model.UserDetailsAdapter;
import edu.miu.lab5.repo.RequestLimiterRepo;
import edu.miu.lab5.repo.UserRepo;
import edu.miu.lab5.service.OffensiveWordService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
@RequiredArgsConstructor
public class WaaOffensiveWordsAspect {

    private static final Set<String> OFFENSIVE_WORDS = Stream.of("spring", "reactjs").collect(Collectors.toSet());

    private final OffensiveWordService offensiveWordService;

    private final RequestLimiterRepo requestLimiterRepo;
    private final UserRepo userRepo;

    @Pointcut("@annotation(edu.miu.lab5.annotation.InspectOffensiveWords)")
    public void inspectOffensiveWordsAnnotation() {

    }

    @Around("inspectOffensiveWordsAnnotation()")
    public Object maskOutOffensiveWords(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Set<Review> reviews = (Set<Review>) args[0];
        Set<String> offensiveWords = new HashSet<>();

        for(Review review : reviews) {
           OFFENSIVE_WORDS.forEach(w -> {
               String comment = review.getComment();
               if(comment.contains(w)) {
                   offensiveWords.add(w);
               }
               String maskedData = comment.replaceAll(w, maskOutData(w));
               review.setComment(maskedData);
           });
        }

        if(!offensiveWords.isEmpty()) {
            UserDetailsAdapter userDetailsAdapter =  (UserDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepo.findByEmail(userDetailsAdapter.getUsername())
                                .orElseThrow(() -> new RuntimeException("Cannot find user: " + userDetailsAdapter.getUsername()));

            RequestLimiter requestLimiter = requestLimiterRepo.findFirstByOrderByCreatedAtDesc();
            if(requestLimiter != null && requestLimiter.getCreatedAt() != null &&
                    requestLimiter.getCreatedAt().plusMinutes(15).isAfter(LocalDateTime.now())) {
                long time = ChronoUnit.MINUTES.between(LocalDateTime.now(), requestLimiter.getCreatedAt().plusMinutes(15));
                throw new RuntimeException("Max Bad Words Requests Limit has been Reached. You need wait for " + time + " minutes.");
            }

            Set<OffensiveWordAudit> offensiveWordsAudits = offensiveWordService.findByLast30Minutes(user.getId());

            if(offensiveWordsAudits.size() >= 5) {
                requestLimiterRepo.save(RequestLimiter.builder()
                        .createdAt(LocalDateTime.now())
                        .user(user)
                        .build());
            }

            OffensiveWordAudit offensiveWordsAudit = OffensiveWordAudit.builder()
                    .createdAt(LocalDateTime.now())
                    .offensiveWords(offensiveWords)
                    .user(user)
                    .build();

            offensiveWordService.save(offensiveWordsAudit);
        }

        return proceedingJoinPoint.proceed();
    }

    private String maskOutData(String word) {
        StringBuilder sb = new StringBuilder();
        sb.append("*".repeat(word.length()));

        return sb.toString();
    }

}
