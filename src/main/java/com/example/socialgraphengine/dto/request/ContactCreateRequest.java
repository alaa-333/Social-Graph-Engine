package com.example.socialgraphengine.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for contact form submission.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactCreateRequest {

    @NotBlank(message = "name.required")
    @Size(max = 100, message = "name.too.long")
    private String name;

    @NotBlank(message = "email.required")
    @Email(message = "email.invalid")
    private String email;

    @Size(max = 20, message = "phone.too.long")
    private String phone;

    @NotBlank(message = "message.required")
    @Size(max = 1000, message = "message.too.long")
    private String message;
}
