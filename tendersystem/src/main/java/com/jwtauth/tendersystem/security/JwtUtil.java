package com.jwtauth.tendersystem.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = 654352132L;

    private static final long JWT_TOKEN_VALIDITY = 500 * 60 * 60;

    String key = Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());

    private final String secretKey = "kZPf9k+EjbDj0P4zMvg+piKZHjK3AHo+ZKk5Wh3ljzFypuTfW0U4HMG+RZVWYbeAGzqTIDzAYJ2uGd49rQ==";

    // Gets the username(email) of the user from token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Retrives the Expiry of the token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Secrte key will be required for retrieving data from token
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    // Check if the token has expired
    public Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    // UserService userService;

    // Generate user for token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // Generate the token from the claims and required details
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    // Check if provided JWT token is valid or not
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String extractedUsername = getUsernameFromToken(token);
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
