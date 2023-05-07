package com.company.instagramapi.config;

import com.company.instagramapi.modal.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl jwtUserDetailsService;

    public JwtTokenGeneratorFilter(UserDetailsServiceImpl jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            SecretKey key=Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
            String jwt= Jwts.builder()
                    .setIssuer("instagram")
                    .setIssuedAt(new Date())
                    .claim("authorities",populateAuthorities(authentication.getAuthorities()))
                    .claim("username",authentication.getName())
                    .setExpiration(new Date(new Date().getTime()+1000*60*60*10))
                    .signWith(key)
                    .compact();
               response.setHeader(HttpHeaders.AUTHORIZATION,jwt);
        }
        filterChain.doFilter(request,response);
    }
    public String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    }

   protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/api/v1/auth/login");
    }
    private void sendError(HttpServletResponse res, Exception e) throws IOException {
        res.setContentType("application/json");
        Map<String, String> errors = new HashMap<>();
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        errors.put("error", e.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        res.getWriter().write(mapper.writeValueAsString(errors));
    }
}
