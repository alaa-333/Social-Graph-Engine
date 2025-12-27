package com.example.socialgraphengine.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating a friend request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestCreateRequest {

    @NotNull(message = "receiver.id.required")
    private Long receiverId;
}
