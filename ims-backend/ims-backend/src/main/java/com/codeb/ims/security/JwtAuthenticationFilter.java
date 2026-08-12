package com.codeb.ims.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codeb.ims.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserRepository userRepository) {

        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");

        // No JWT token
        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {

            // Check JWT validity
            if (!jwtService.isTokenValid(token)) {

                filterChain.doFilter(request, response);
                return;
            }

            // Get email from JWT
            String email =
                    jwtService.extractEmail(token);

            if (email == null) {

                filterChain.doFilter(request, response);
                return;
            }

            // Don't authenticate twice
            if (SecurityContextHolder
                    .getContext()
                    .getAuthentication() == null) {

                // Find user in database
                com.codeb.ims.entity.User databaseUser =
                        userRepository
                                .findByEmail(email)
                                .orElse(null);

                if (databaseUser == null) {

                    filterChain.doFilter(request, response);
                    return;
                }

                // Get role
                String role =
                        databaseUser.getRole().name();

                // Create authority
                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority(
                                "ROLE_" + role
                        );

                // Create authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(authority)
                        );

                // Add request details
                authentication.setDetails(
                        new org.springframework.security.web
                                .authentication
                                .WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // Set authenticated user
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(
                                authentication
                        );
            }

        } catch (Exception e) {

            SecurityContextHolder
                    .clearContext();
        }

        // Continue request
        filterChain.doFilter(request, response);
    }
}