package com.uni_verso.uni_verso.util;

import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.uni_verso.uni_verso.api.dto.response.UserResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

    private String secret;
    private long expiration;

    public String createToken(UserResponse user) {
        Algorithm algorithm = Algorithm.HMAC256(this.secret);

        return JWT.create()
                .withSubject(user.getId())
                .withClaim("icon", user.getIcon())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Invalid token");
        }
    }

}
