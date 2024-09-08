package com.devchw.timetrace;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
@Profile({"local", "local-dev"})
public class TimeTraceAspect {

    @Pointcut("@annotation(com.devchw.timetrace.TimeTrace)")
    private void timeTracePointcut() {

    }

    @Around("timeTracePointcut()")
    public Object timeTraceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            return joinPoint.proceed(); // 타겟 호출
        } finally {
            stopWatch.stop();
            log.info("{} - Total time - {}s",
                    joinPoint.getSignature().toShortString(),
                    stopWatch.getTotalTimeSeconds());
        }
    }

}
