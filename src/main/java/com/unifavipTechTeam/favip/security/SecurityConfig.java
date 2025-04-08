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
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/logout").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/validate-token").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register-admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/email/send").permitAll()
                        .requestMatchers(HttpMethod.POST, "/email/send-first-acess-code").permitAll()
                        .requestMatchers(HttpMethod.POST, "/recovery/verify").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/recovery-password/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/recovery/get-valid/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/star/give").permitAll()
                        .requestMatchers(HttpMethod.GET, "/star/get/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/star/delete/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/courses/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/courses/").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/personalData/**").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/personalData/**").hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/diversity/**").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/diversity/**").hasAnyRole( "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/experience/**").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/experience/**").hasAnyRole( "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/formation/**").hasAnyRole( "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/formation/**").hasAnyRole( "ADMIN")

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