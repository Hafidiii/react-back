package com.example.storemanagement.config;

import com.example.storemanagement.service.serviceImpl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private UserDetailsServiceImpl userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-ui/**")).hasRole("ADMIN")

                );

        httpSecurity.formLogin();
        httpSecurity.authorizeHttpRequests().anyRequest().permitAll();
        httpSecurity.headers(headers -> headers.frameOptions().disable());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        httpSecurity.userDetailsService(userDetailsService);

        return httpSecurity.build();
    }


}
