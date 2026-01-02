package com.example.socialgraphengine.mapper;

import com.example.socialgraphengine.dto.request.UserCreateRequest;
import com.example.socialgraphengine.dto.request.UserUpdateRequest;
import com.example.socialgraphengine.dto.response.UserResponse;
import com.example.socialgraphengine.model.User;
import com.example.socialgraphengine.model.enums.Role;
import org.mapstruct.*;

import java.lang.annotation.Target;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring" ,
        imports = {
                Set.class,
                Role.class
        })
public interface UserMapper {

    // ========== to entity ============
    @Mapping(target = "roles" , expression = "java(Set.of(Role.ROLE_USER)")
    User toEntity(UserCreateRequest request);


    // ======== To Response ===========
    UserResponse toResponse(User user);

    default void updateUserFromDto(@MappingTarget User user , UserUpdateRequest request)
    {
        if (Objects.isNull(request))
        {
            return ;
        }
        Optional.ofNullable(request.getEmail())
                .ifPresent(email -> user.setEmail(email));
        Optional.ofNullable(request.getPassword())
                .ifPresent(pass -> user.setPassword(pass));
        Optional.ofNullable(request.getEnabled())
                .ifPresent(value -> user.setEnabled(value));
        Optional.ofNullable(request.getAccountNonLocked())
                .ifPresent(value -> user.setAccountNonLocked(value));

    }
}
