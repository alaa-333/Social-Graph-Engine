package com.example.socialgraphengine.filter;

import com.example.socialgraphengine.config.UserDetailsServiceImpl;
import com.example.socialgraphengine.helper.JwtService;
import com.example.socialgraphengine.service.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String username = "";
        String token = "";

        // if no token delegate to next filter
        if(Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request , response);
            return;
        }

        token = authHeader.substring(7);

        if (!jwtService.validateToken(token))
        {
            throw new JwtException("token not valid");
        }

        username = jwtService.extractUsername(token);

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (!jwtService.validateToken(token, user)) {
            throw new JwtException("error occurred here");
        }

        Set<SimpleGrantedAuthority> roles = user.getAuthorities()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getAuthority()))
                .collect(Collectors.toSet());

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null , roles);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

}
