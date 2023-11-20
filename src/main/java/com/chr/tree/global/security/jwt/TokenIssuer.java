package com.chr.tree.global.security.jwt;

import com.chr.tree.global.security.jwt.properties.JwtProperties;
import com.chr.tree.global.security.jwt.properties.TokenTimeProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Getter
@Component
@RequiredArgsConstructor
public class TokenIssuer {

    private final TokenTimeProperties tokenTimeProperties;
    private final JwtProperties jwtProperties;

    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 120;

    @AllArgsConstructor
    private enum TokenObject {
        ACCESS_TYPE("access"),
        REFRESH_TYPE("refresh"),
        TOKEN_PREFIX("Bearer "),
        AUTHORITY("authority");
        final String value;
    }

    public String generateToken(String email, String type, Key secret, Long exp) {

        final Claims claims = Jwts.claims().setSubject(email);

        claims.put("type", type);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .signWith(secret, SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String generateAccessToken(String email) {
        return generateToken(email, TokenObject.ACCESS_TYPE.value, jwtProperties.getAccessSecret(), tokenTimeProperties.getAccessTime());
    }

    public String generateRefreshToken(String email) {
        return generateToken(email, TokenObject.REFRESH_TYPE.value, jwtProperties.getRefreshSecret(), tokenTimeProperties.getRefreshTime());
    }
}
