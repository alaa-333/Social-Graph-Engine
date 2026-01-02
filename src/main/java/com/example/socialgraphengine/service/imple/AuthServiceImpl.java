package com.example.socialgraphengine.service.imple;

import com.example.socialgraphengine.dto.request.AuthRequest;
import com.example.socialgraphengine.dto.request.UserCreateRequest;
import com.example.socialgraphengine.dto.response.TokenResponse;
import com.example.socialgraphengine.exception.DuplicateResourceException;
import com.example.socialgraphengine.exception.ErrorCode;
import com.example.socialgraphengine.helper.JwtService;
import com.example.socialgraphengine.repository.UserRepository;
import com.example.socialgraphengine.service.AuthService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;
    private final JwtService jwtService;


    @Override
    public TokenResponse login(AuthRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // ==== generate token =====
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.refreshToken(userDetails.getUsername());
        String expiration = jwtService.extractClaim(accessToken , Claims::getExpiration).toString();

        return  TokenResponse.builder()
                .subject(userDetails.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiration(expiration)
                .roles(List.of(userDetails.getAuthorities().toString()))
                .build();
    }

    @Override
    public TokenResponse register(UserCreateRequest request) {

        if (userRepository.existByEmail(request.getEmail())) {
            throw new DuplicateResourceException(ErrorCode.DUPLICATE_ENTRY);
        }

        UserDetails user = (UserDetails) userServiceImpl.createUser(request);
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.refreshToken(request.getEmail());

        return TokenResponse.builder()
                .subject(user.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiration(jwtService.extractClaim(accessToken, Claims::getExpiration).toString())
                .roles(List.of(user.getAuthorities().toString()))
                .build();

    }
}
