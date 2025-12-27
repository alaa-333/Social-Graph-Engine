package com.example.socialgraphengine.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating a friendship.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipCreateRequest {

    @NotNull(message = "friend.id.required")
    private Long friendId;
}
