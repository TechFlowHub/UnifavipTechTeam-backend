package com.unifavipTechTeam.favip.security;

import com.unifavipTechTeam.favip.security.CustomUserDetailsService;
import com.unifavipTechTeam.favip.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/email/send").permitAll()
                        .requestMatchers(HttpMethod.POST, "/email/sendFirstAcessCode").permitAll()
                        .requestMatchers(HttpMethod.POST, "/recovery/verify").permitAll()
                        
                        .requestMatchers(HttpMethod.POST, "/courses/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/courses/").hasAnyRole("USER", "ADMIN")
                        
                        .requestMatchers(HttpMethod.POST, "/personalData/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/personalData/**").hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/diversity/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/diversity/**").hasAnyRole("USER", "ADMIN")
                        
                        .requestMatchers(HttpMethod.POST, "/experience/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/experience/**").hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}