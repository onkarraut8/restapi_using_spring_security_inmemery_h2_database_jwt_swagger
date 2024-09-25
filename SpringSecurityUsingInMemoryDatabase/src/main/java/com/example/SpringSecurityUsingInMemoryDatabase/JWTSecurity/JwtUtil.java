package com.example.SpringSecurityUsingInMemoryDatabase.JWTSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtil {

    //@Value("${jwt.secret}")
    private String secretKey="1234dxdgsd34";

    //@Value("${jwt.expiration}")
    private long expiration=86400000;

    private final String SECRET_KEY = "1234dxgchghgggdshjfchgfhjhjgjffhfhffgccsgfxfgcgfdgsd34"; // Change this to a secure key
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour in milliseconds

    // Method to generate token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        return createToken(claims, username);
    }

    // Method to create the token
    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        Key signingKey = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        JwtBuilder builder = Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(signingKey, SignatureAlgorithm.HS256);
        
        return builder.compact();
    }

    // Method to validate the token
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Method to extract username from token
    public String extractUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }

    // Method to check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Method to extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName()))
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
