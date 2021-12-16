package bg.softuni.yacht.aop;

import bg.softuni.yacht.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* bg.softuni.yacht.web.TourController.tourDetails(..))")
    public void tourDetailsPointcut(){}

    @After("tourDetailsPointcut()")
    public void afterAdvice(JoinPoint joinPoint){

        Object[] arg = joinPoint.getArgs();
        Long tourId = (Long) arg[0];
        String action = joinPoint.getSignature().getName();

        logService.createLog(action, tourId);



    }
}
