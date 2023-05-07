package com.company.instagramapi.security;

import com.company.instagramapi.config.SecurityContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtTokenProvider {
    public JwtTokenClaims getClaimsFromToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
        Claims claims = io.jsonwebtoken.Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        String username = claims.get("username").toString();
        JwtTokenClaims jwtTokenClaims = new JwtTokenClaims();
        jwtTokenClaims.setUsername(username);
        return jwtTokenClaims;
    }
}
