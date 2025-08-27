package com.example.demo.interceptor;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HeaderForwardingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        String userId = MDC.get("userId");
        String transactionId = MDC.get("transactionId");

        if (userId != null) {
            request.getHeaders().add("X-User-Id", userId);
        }
        if (transactionId != null) {
            request.getHeaders().add("Tx-Id", transactionId);
        }

        return execution.execute(request, body);
    }
}