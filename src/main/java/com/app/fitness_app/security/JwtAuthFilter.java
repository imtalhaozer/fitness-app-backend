package com.app.fitness_app.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.fitness_app.service.JwtService;
import com.app.fitness_app.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private UserService userService;
    private JwtService jwtService;
    public JwtAuthFilter(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader= request.getHeader("Authorozition");
        String token=null;
        String userName=null;

        if(authHeader!=null && authHeader.startsWith("Bearer")){
            token=authHeader.substring(7);
            userName=jwtService.exractUser(token);
        }

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails user= userService.loadUserByUserName(userName);
            if(jwtService.validateToken(token, user)){
                UsernamePasswordAuthenticationToken authToken
                        = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);;

            }
        }
        filterChain.doFilter(request, response);
    }
    
}
