package com.bike.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.bike.security.component.OAuth2AuthenticationSuccessHandler;
import com.bike.security.component.OAuth2FailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            OAuth2AuthenticationSuccessHandler successHandler, OAuth2FailureHandler failureHandler) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/api/login","/api/swagger-ui.html",
                                "/api/swagger-ui.html",
                                "/api/swagger-ui/**",

                                // OpenAPI
                                "/api/v3/api-docs/**",
                                "/v3/api-docs/**",

                                // Swagger resources, if applicable
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/auth/createuser"
                        )
                       .permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)

                )
                 .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
