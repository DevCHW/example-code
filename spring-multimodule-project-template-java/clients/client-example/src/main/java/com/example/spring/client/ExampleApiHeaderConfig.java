package com.example.spring.client;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

// 공통 헤더 속성 추가 설정
public class ExampleApiHeaderConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate
                    .header(AUTHORIZATION, "Bearer " + "MY_TOKEN")
                    .header(CONTENT_TYPE, "application/json");
        };
    }

}
