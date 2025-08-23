package co.com.marimaro.pizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers(HttpMethod.GET, "/customers/**").hasAnyRole("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/pizzas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pizzas/**").hasRole("ADMIN")
                        .requestMatchers("/orders/create/random").hasAuthority("random_order")
                        .requestMatchers("/orders/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build(); //
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
