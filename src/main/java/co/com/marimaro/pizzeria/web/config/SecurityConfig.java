package co.com.marimaro.pizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers(HttpMethod.GET, "/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/pizzas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pizzas/**").hasRole("ADMIN")
                        .requestMatchers("/orders/create/random").hasAuthority("random_order")
                        .requestMatchers("/orders/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build(); //
    }

    // @Bean
    // public UserDetailsService memoryUsers() {
    // UserDetails admin = User.builder()
    // .username("admin")
    // // La nueva contraseña debe estar encriptada, si se envía una cadena de texto
    // // genera error
    // .password(passwordEncoder().encode("admin"))
    // .roles("ADMIN")
    // .build();
    // // Teniendo este usuario Spring dejará de crear un usuario por defecto

    // UserDetails customer = User.builder()
    // .username("customer")
    // .password(passwordEncoder().encode("customer"))
    // .roles("CUSTOMER")
    // .build();

    // return new InMemoryUserDetailsManager(admin, customer);
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
