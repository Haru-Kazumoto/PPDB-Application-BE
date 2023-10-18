package dev.pack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        // Mengizinkan semua origin
        configuration.addAllowedOrigin("*");

        // Mengizinkan semua header
        configuration.addAllowedHeader("*");

        // Mengizinkan semua method (GET, POST, PUT, dll.)
        configuration.addAllowedMethod("*");

        // Mengatur max age (durasi cache) dalam detik
        configuration.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }
}
