package com.experis.experiscodingtestback.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Slf4j

public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityService securityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            System.out.println("request dofilter..." + request);
            verifyToken(request);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }

    private void verifyToken(HttpServletRequest request) throws FirebaseAuthException {
        System.out.println("request: " + request);
        String token = securityService.getBearerToken(request);
        System.out.println("token: " + token);
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        System.out.println("decoded token: " + decodedToken);

    }
}
