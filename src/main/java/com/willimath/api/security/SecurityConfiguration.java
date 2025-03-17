package com.willimath.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private KeycloakTokenConverter keycloakTokenConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers ->
                        headers
                                .contentSecurityPolicy(csp ->
                                        csp.policyDirectives("default-src 'self' data:; style-src 'self' 'unsafe-inline';")
                                )
                                .frameOptions(frameOptionsConfig -> frameOptionsConfig.deny())
                                .permissionsPolicyHeader(permissionsPolicyConfig -> permissionsPolicyConfig.policy(
                                        "fullscreen=(self), geolocation=(), microphone=(), camera=()"
                                ))
                )
                .authorizeHttpRequests(authorizations ->
                        authorizations
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/accounts/token").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user/**").hasAuthority("User")
                                .requestMatchers(HttpMethod.POST, "/user/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/user/**").hasAuthority("User")
                                .requestMatchers(HttpMethod.DELETE, "/user/**").hasAuthority("Admin")
                                .requestMatchers(HttpMethod.GET, "/search/**").hasAuthority("User")
                                .requestMatchers(HttpMethod.GET, "/trip/**").hasAuthority("User")
                                .requestMatchers(HttpMethod.POST, "/trip/**").hasAuthority("User")
                                .requestMatchers(HttpMethod.PUT, "/trip/**").hasAuthority("User")
                                .requestMatchers(HttpMethod.DELETE, "/trip/**").hasAuthority("User")
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(keycloakTokenConverter))
                );
        return http.build();
    }

}
