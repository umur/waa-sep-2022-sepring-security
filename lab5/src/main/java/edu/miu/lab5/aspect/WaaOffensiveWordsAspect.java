package edu.miu.lab5.aspect;

import edu.miu.lab5.entity.Product;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class WaaOffensiveWordsAspect {

    private WaaOffensiveWordsService waaOffensiveWordsService;

    public WaaOffensiveWordsAspect(WaaOffensiveWordsService waaOffensiveWordsService) {
        this.waaOffensiveWordsService = waaOffensiveWordsService;
    }

    @Pointcut("@annotation(edu.miu.lab5.aspect.WaaOffensiveAnotate)")
    public  void pointCut(){}

    @Around("pointCut() && args(.., @RequestBody body)")
    public Object run(ProceedingJoinPoint proceedingJoinPoint,Object body) throws Throwable {


        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var product=(Product) body;

        waaOffensiveWordsService.CheckWordForUser(userDetails.getUsername(),product.getName(),product);


        return proceedingJoinPoint.proceed();
    }

}
