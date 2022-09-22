package henry.springsecurity.aop;

import henry.springsecurity.entity.OffensiveWordsHistory;
import henry.springsecurity.entity.RequestLimit;
import henry.springsecurity.entity.Review;
import henry.springsecurity.entity.User;
import henry.springsecurity.repo.RequestLimitRepo;
import henry.springsecurity.repo.UserRepo;
import henry.springsecurity.security.MyUserDetails;
import henry.springsecurity.service.OffensiveWordsHistoryService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Aspect
@Component
@RequiredArgsConstructor
public class WaaOffensiveWordsAspect {
    private static final HashMap<String, String> OFFENSIVE_WORDS = initMap();
    private final OffensiveWordsHistoryService offensiveWordsHistoryService;
    private final RequestLimitRepo requestLimitRepo;
    private final UserRepo userRepo;

    @Pointcut("@annotation(henry.springsecurity.aop.annotation.CheckOffensive)")
    public void checkOffensive() {
    }

    @Around("checkOffensive()")
    public Object filterOffensiveWords(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Set<Review> reviews = (Set<Review>) args[0];
        Set<String> offensiveWords = new HashSet<>();

        for (Review review : reviews) {
            OFFENSIVE_WORDS.forEach((word, replace) -> {
                String comment = review.getComment();
                if (comment.contains(word)) {
                    offensiveWords.add(word);
                    String filteredComment = comment.replaceAll(word, replace);
                    review.setComment(filteredComment);
                }
            });
        }

        if (!offensiveWords.isEmpty()) {
            MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepo.findByEmail(myUserDetails.getUsername());
            if (user == null) throw new RuntimeException("Cannot find user: " + myUserDetails.getUsername());

            RequestLimit requestLimit = requestLimitRepo.findFirstByUser_IdOrderByAtDesc(user.getId());
            if (requestLimit != null && requestLimit.getAt() != null &&
            requestLimit.getAt().plusMinutes(15).isAfter(LocalDateTime.now())) {
                long time = ChronoUnit.MINUTES.between(LocalDateTime.now(), requestLimit.getAt().plusMinutes(15));
                throw new RuntimeException("Max Bad Words Requests Limit has been Reached. You need wait for " + time + " minutes.");
            }

            Set<OffensiveWordsHistory> offensiveWordsHistories = offensiveWordsHistoryService.findByLast30Min(user.getId());
            if (offensiveWordsHistories.size() > 5) {
                requestLimitRepo.save(RequestLimit.builder().user(user).at(LocalDateTime.now()).build());
            }

            OffensiveWordsHistory offensiveWordsHistory = OffensiveWordsHistory.builder()
                    .at(LocalDateTime.now())
                    .offensiveWords(offensiveWords)
                    .user(user)
                    .build();
            offensiveWordsHistoryService.save(offensiveWordsHistory);
        }

        return proceedingJoinPoint.proceed();
    }

    private static HashMap<String, String> initMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Spring", "******");
        hashMap.put("JPA", "***");
        hashMap.put("Hibernate", "*********");
        return hashMap;
    }
}
