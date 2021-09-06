package com.experis.experiscodingtestback.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPointJwt)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/questions/test").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/users/checkuser").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/answers/user/{userId}").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(getAllowedOrigins());
        configuration.setAllowedMethods(getAllowedMethods());
        configuration.setAllowedHeaders(getAllowedHeaders());
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private List<String> getAllowedOrigins() {
        List<String> origins = new ArrayList<>();
        origins.add("https://experis-coding-test.vercel.app/");
        origins.add("https://experis-coding-test-admin.vercel.app/");
        origins.add("http://localhost:3000/");
        origins.add("http://localhost:3001/");
        return origins;
    }

    private List<String> getAllowedMethods() {
        List<String> methods = new ArrayList<>();
        methods.add("GET");
        methods.add("POST");
        methods.add("PUT");
        methods.add("DELETE");
        return methods;
    }

    private List<String> getAllowedHeaders() {
        List<String> headers = new ArrayList<>();
        headers.add("Authorization");
        headers.add("Access-Control-Allow-Origin");
        headers.add("content-type");
        return headers;
    }
}
