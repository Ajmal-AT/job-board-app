package com.project.job_board_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Value("${cors.env}")
    private String corsEnv;
    @Value("${cors.env.prod}")
    private String corsEnvProd;

    @Bean
    public CorsFilter corsFilter() {

        List<String> cors = Arrays.asList(corsEnv, corsEnvProd);
        System.out.println("cors in cos :" + cors);

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(cors);
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
