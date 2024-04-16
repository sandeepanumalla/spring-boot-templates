package org.secured.securedwebsockets.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true)
//                .allowedHeaders("Authorization", "Cache-Control", "Content-Type")
//                .allowedOrigins("http://localhost:3000", "http://localhost:3001")
//                .allowedMethods("GET", "POST", "PUT", "DELETE");
//    }
}
