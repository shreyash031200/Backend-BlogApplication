package com.blog.blogapplication.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticatorFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

//        1. get token
    String requestToken = request.getHeader("Authorization");
    System.out.println(requestToken);

    String username= null;
    String token = null;

    if(requestToken != null && requestToken.startsWith("Bearer")){

         token = requestToken.substring(7);
        try {
            username = this.jwtTokenHelper.extractUsername(token);
        }catch (IllegalArgumentException e){
            System.out.println("enable to get JWT");
        }catch (ExpiredJwtException e){
            System.out.println("Jwt token has expired");
        }catch (MalformedJwtException e){
            System.out.println("Invalid JWT");
        }
    }else{
        System.out.println("JWT does not begin with Bearer");
    }

    if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        if(this.jwtTokenHelper.validateToken(token, userDetails)){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }else{
            System.out.println("invalid jwt token");
        }
    }else {
        System.out.println("username is null or context is not null");
    }
    filterChain.doFilter(request,response);
    }
}
