package edu.miu.demo.aspect;

import edu.miu.demo.model.Counter;
import edu.miu.demo.repo.CounterRepo;
import edu.miu.demo.security.WaaUserDetails;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class WaaOffensiveWords {

    private final CounterRepo repository;

    @Pointcut("@annotation(WaaWord)")
    public void annotated() {}

    @Around("annotated()")
    public Object replaceOffensiveWord(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("annotated");
        List<String> list = (List<String>) joinPoint.proceed();
        list = list.stream().filter(item -> item.contains("spring"))
                .map(content -> {
                    content = content.replace("spring", "******");
                    return content;
                }).collect(Collectors.toList());
        WaaUserDetails userDetails = (WaaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Counter counter = new Counter(list.stream().filter(item -> item.contains("spring")).collect(Collectors.joining(", ")), userDetails.getId());
        repository.save(counter);
        return list;
    }
}

