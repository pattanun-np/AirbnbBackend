package org.armzbot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.armzbot.controller.ErrorAdviser;
import org.armzbot.services.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final ObjectMapper mapper;

    private void handleInvalidToken(HttpServletResponse response) throws IOException {
        ErrorAdviser.ErrorResponse errorResponse = new ErrorAdviser.ErrorResponse();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        errorResponse.setError("Invalid Token provided");
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }

    private void handleTokenMissing(HttpServletResponse response) throws IOException {
        ErrorAdviser.ErrorResponse errorResponse = new ErrorAdviser.ErrorResponse();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        errorResponse.setError("Token missing");
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/auth")) {
//            System.out.println("Authenticating");

            filterChain.doFilter(request, response);
            return;
        }
//        System.out.println("Authenticating");
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            handleTokenMissing(response);

            return;

        }

        jwt = authHeader.substring(7);
//        System.out.println(jwt);
        try {
            FirebaseToken decodedToken = tokenService.verify(jwt);
            String uid = decodedToken.getUid();

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(uid, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (FirebaseAuthException e) {
            handleInvalidToken(response);

        }


    }
}
