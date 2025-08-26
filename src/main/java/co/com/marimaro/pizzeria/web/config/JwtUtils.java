package co.com.marimaro.pizzeria.web.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtils {
    private static final Algorithm ALGORITHM;

    static {
        String SECRET_KEY = "m4r1m4r0_p1zz4";
        ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    }

    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("marimaro-pizza")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }
}
