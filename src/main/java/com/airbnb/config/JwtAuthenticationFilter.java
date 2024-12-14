package com.airbnb.config;

import com.airbnb.dto.response.common.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private ObjectMapper objectMapper;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            processAuthorizationHeader(request);
        } catch (JwtException | IllegalArgumentException e) {
            handleUnauthorizedResponse(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Processes the "Authorization" header from the HTTP request to extract and validate a JWT token.
     * If the token is valid, the method retrieves the user details and sets the authentication
     * in the {@link org.springframework.security.core.context.SecurityContext}.
     *
     * @param request the HTTP servlet request containing the Authorization header
     */
    private void processAuthorizationHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            String token = authorizationHeader.replace(BEARER_PREFIX, "");
            String username = jwtUtil.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(token)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
    }

    /**
     * Handles unauthorized responses by returning a JSON response with an HTTP 401 status code.
     * The response includes a message indicating that the JWT token is invalid or expired.
     *
     * @param response the HttpServletResponse object used to send the unauthorized response
     * @throws IOException if an I/O error occurs while writing the response
     */
    private void handleUnauthorizedResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        CommonResponse<String> errorResponse = new CommonResponse<>(
                HttpStatus.UNAUTHORIZED.value(),
                "JWT token is invalid or expired",
                null
        );
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
    }
}