package com.emr.ikdemobackend.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JWTFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);                                   //get auth header from request
        if ((authHeader != null) && (authHeader.startsWith("Bearer"))) {
            try {
                String token = authHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("BU-SIFREYI-KIMSE-COZEMEZ!");     //get algorithm with secret given during encryption
                JWTVerifier verifier = JWT.require(algorithm).build();                          //
                DecodedJWT decodedJWT = verifier.verify(token);                                 //decode jwt
                String username = decodedJWT.getSubject();                                      //get username from decoded token
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);         //get roles from decoded token
                ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
                Arrays.stream(roles).forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role));                          //add authorities to arraylist
                });
                UsernamePasswordAuthenticationToken authToken =                                 //wrap username and authorities
                        new UsernamePasswordAuthenticationToken(username,null,authorities);
                SecurityContextHolder.getContext().setAuthentication(authToken);                //give authorities to user
                filterChain.doFilter(request,response);
            } catch (Exception e){
                response.setStatus(FORBIDDEN.value());
                Map<String, String> err = new HashMap<>();
                err.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), err);
            }
        } else {
            filterChain.doFilter(request,response);
        }

    }
}
