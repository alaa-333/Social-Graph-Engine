package com.example.socialgraphengine.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for following an account.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowCreateRequest {

    @NotNull(message = "following.id.required")
    private Long followingId;
}
