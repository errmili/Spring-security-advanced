package com.spring.securityadvanced.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.securityadvanced.constants.SecurityConstant;

public class JWTTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstant.KEY.getBytes(StandardCharsets.UTF_8));
                String jwt = Jwts.builder().setSubject("Jwt Token")
                        .claim("email", authentication.getName())
                        .claim("authorities", authorities(authentication.getAuthorities()))
                        .setIssuedAt(new Date()) // 2022/8/27 7:00
                        .setExpiration(new Date((new Date().getTime() + 30000)))//2022/8/27 7:00 30s
                        .signWith(secretKey).compact();
                response.setHeader(SecurityConstant.HEADER, jwt);
            }
        } catch (Exception e) {
            System.out.println(e.toString());

        }
        filterChain.doFilter(request,response);
    }
    // filter     login ✔
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // NO Filter      true
        // NO Filter      false     -> filter
      //  return !request.getServletPath().equals("/login"); //
        return !request.getServletPath().equals("/user");
        //  /user     true
        //  /login   false
    }
    private String authorities(Collection<? extends GrantedAuthority> collections){
        Set<String> auth = new HashSet<>();//"user,admin"
        for (GrantedAuthority authority: collections){
            auth.add(authority.getAuthority());
        }
        return String.join(",",auth);
    }
}
