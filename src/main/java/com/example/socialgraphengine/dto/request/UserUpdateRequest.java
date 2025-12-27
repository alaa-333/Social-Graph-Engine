package com.example.socialgraphengine.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for updating user information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @Email(message = "email.invalid")
    private String email;

    @Size(min = 6, max = 100, message = "password.length.invalid")
    private String password;

    private Boolean enabled;

    private Boolean accountNonLocked;
}
