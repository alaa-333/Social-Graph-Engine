package com.example.socialgraphengine.controller;

import com.example.socialgraphengine.dto.request.AuthRequest;
import com.example.socialgraphengine.dto.request.UserCreateRequest;
import com.example.socialgraphengine.dto.response.TokenResponse;
import com.example.socialgraphengine.service.imple.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody @Valid UserCreateRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
