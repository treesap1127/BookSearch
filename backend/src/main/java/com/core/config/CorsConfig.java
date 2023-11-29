package com.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Value("${cors.url}")
    private String url;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println(url);
        registry.addMapping("/api/**")
                .allowedOrigins(url) // 허용할 오리진(도메인) 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드 설정
                .allowCredentials(true) // 쿠키를 허용할지 여부
                .maxAge(3600); // CORS preflight 요청의 유효 시간 설정 (초)
    }
}
