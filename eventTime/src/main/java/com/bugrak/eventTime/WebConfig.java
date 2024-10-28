package com.bugrak.eventTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // API yolları için
                .allowedOrigins("http://127.0.0.1:5500") // İzin verilen kaynak
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // İzin verilen HTTP metotları
                .allowedHeaders("*") // İzin verilen başlıklar
                .allowCredentials(true); // Kimlik bilgilerini gönderme izni
    }
}
