package co.com.marimaro.pizzeria.web.controller;

import co.com.marimaro.pizzeria.service.dto.login.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.marimaro.pizzeria.service.dto.login.LoginDto;
import co.com.marimaro.pizzeria.web.config.JwtUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(dto.getUsername(),
                dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(login);
        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(Map.of("Error", "No se ha podido realizar la autenticación."));
        } else {
            String jwt = jwtUtil.create(dto.getUsername());
            LoginResponseDto response = new LoginResponseDto();
            response.setToken_type("Bearer");
            response.setToken(jwt);
            response.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)));
            return ResponseEntity.ok().body(response);
        }
    }
}
