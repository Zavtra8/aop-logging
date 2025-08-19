package com.example.demo.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.demo.controller..*(..)) || execution(* com.example.demo.service..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        // Set MDC metadata
        MDC.put("event", "ENTRY");
        MDC.put("method", methodName);

        logger.info("Method {} called with args {}", methodName, Arrays.toString(args));

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;

            MDC.put("event", "EXIT");
            logger.info("Method {} returned with result {} ({} ms)", methodName, result, duration);

            return result;
        } catch (Throwable ex) {
            MDC.put("event", "EXCEPTION");
            logger.error("Exception in method {}: {}", methodName, ex.getMessage(), ex);
            throw ex;
        } finally {
            // Clean up MDC
            MDC.remove("event");
            MDC.remove("method");
        }
    }
}