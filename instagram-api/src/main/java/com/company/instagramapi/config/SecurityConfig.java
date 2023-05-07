package com.company.instagramapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenGeneratorFilter jwtTokenGeneratorFilter;
    private final JwtTokenValidationFilter jwtTokenValidationFilter;
    public SecurityConfig(JwtTokenGeneratorFilter jwtTokenGeneratorFilter, JwtTokenValidationFilter jwtTokenValidationFilter) {
        this.jwtTokenGeneratorFilter = jwtTokenGeneratorFilter;
        this.jwtTokenValidationFilter = jwtTokenValidationFilter;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests(auth-> {
                           auth.requestMatchers(HttpMethod.POST,"/api/v1/auth/register").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/api/v1/auth/login").permitAll();
                    auth.anyRequest().authenticated();
                        }
                )
                .addFilterBefore(jwtTokenValidationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtTokenGeneratorFilter,BasicAuthenticationFilter.class)
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .build();
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("*");
//            }
//        };
//    }
}
