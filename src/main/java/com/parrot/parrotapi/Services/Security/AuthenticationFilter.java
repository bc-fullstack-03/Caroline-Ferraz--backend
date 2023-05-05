package com.parrot.parrotapi.Services.Security;

import com.parrot.parrotapi.Services.User.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private IJwtService _jwtService;

    @Autowired
    private IUserService _userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().contains("api/v1/authentication")){
            filterChain.doFilter(request, response);
        }
        if(request.getServletPath().contains("swagger") || request.getServletPath().contains("docs")){
            filterChain.doFilter(request, response);
        }

        var token = request.getHeader("Authorization");
        var userId = request.getHeader("RequestedBy");

        if(token == null || userId == null || !token.startsWith("Bearer ")){
            response.getWriter().write("Credenciais inválidas.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        boolean isInvalidToken = false;

        try {
            isInvalidToken = _jwtService.isValidToken(token.substring(7), userId);
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
