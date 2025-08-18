package co.com.marimaro.pizzeria.web.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        // Define la configuración CORS
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // Define los orígenes desde los que se permite consumir
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        // Define los métodos que permite consumir
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        // Define que headers debe permitir por el CORS
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica la política CORS a todos los controladores, esto por el "/**"
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
