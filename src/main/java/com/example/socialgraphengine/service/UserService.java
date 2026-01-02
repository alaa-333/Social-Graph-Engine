package com.example.socialgraphengine.service;

import com.example.socialgraphengine.dto.request.UserCreateRequest;
import com.example.socialgraphengine.dto.request.UserUpdateRequest;
import com.example.socialgraphengine.dto.response.UserResponse;
import com.example.socialgraphengine.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface UserService {

    User createUser(UserCreateRequest request);

    UserResponse getUserById(Long id);

    UserResponse updateUserById(Long id, UserUpdateRequest request);

    void deleteUserById( Long id);
}
