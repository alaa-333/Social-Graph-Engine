package com.example.socialgraphengine.service;

import com.example.socialgraphengine.dto.request.AuthRequest;
import com.example.socialgraphengine.dto.request.UserCreateRequest;
import com.example.socialgraphengine.dto.response.TokenResponse;

public interface AuthService {

    TokenResponse login (AuthRequest request);
    TokenResponse register (UserCreateRequest request);
}
