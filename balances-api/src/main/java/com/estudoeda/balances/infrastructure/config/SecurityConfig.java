package com.estudoeda.balances.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) {
        http
            .headers(headers -> headers
                .contentTypeOptions(contentType -> contentType.disable())
                .referrerPolicy(ref -> ref
                    .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN))
                .frameOptions(frame -> frame
                    .sameOrigin())
                .httpStrictTransportSecurity(hsts -> hsts.disable())
                .cacheControl(cache -> cache.disable()));

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/actuator/health",
                    "/actuator/info",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html")
                .permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/**").permitAll()
                // .requestMatchers("/v1/**").authenticated()
                .anyRequest().authenticated())
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}