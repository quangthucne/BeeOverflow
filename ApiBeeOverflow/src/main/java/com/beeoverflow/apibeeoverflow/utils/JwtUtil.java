package com.beeoverflow.apibeeoverflow.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "ZDc3MmZkNjctM2UxMi00NzcyLTgxOGEtNTQ3NzFhNjdkZWNj";
    private final SecretKey ENCODE_SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));

    public String generateToken(String username, String id, String role) {
        // Ensure role has the "ROLE_" prefix
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        // Api login --> tạo ra jwt, refresh token
        // Api refresh --> tạo lại jwt trong phiên làm việc
        // jwt --> lưu về phí client

        return Jwts.builder()
                .setSubject(username)
                .claim("accountId", id)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(ENCODE_SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(ENCODE_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return Jwts.parser().setSigningKey(ENCODE_SECRET_KEY).parseClaimsJws(token).getBody().get("role", String.class);
    }

    public int extractUserId(String token) {
        return Integer.parseInt(Jwts.parser().setSigningKey(ENCODE_SECRET_KEY).parseClaimsJws(token).getBody().get("accountId", String.class));
    }
}
