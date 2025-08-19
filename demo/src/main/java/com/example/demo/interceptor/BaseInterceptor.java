package com.example.demo.interceptor;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

public class BaseInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String userId = request.getHeader("X-User-Id");
        if (userId == null || userId.isBlank()) {
            userId = "HERO";
        }

        String transactionId = request.getHeader("Tx-Id");
        if(transactionId == null || transactionId.isBlank()){
            transactionId = UUID.randomUUID().toString();
        }

        MDC.put("userId", userId);
        MDC.put("transactionId", transactionId);

        Transaction transaction = ElasticApm.currentTransaction();
        transaction.setUser(userId, null, null);
        transaction.addLabel("userId", userId);
        transaction.addLabel("transactionId", transactionId);
        transaction.setName(request.getMethod() + " " + request.getRequestURI());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        MDC.clear();
    }
}