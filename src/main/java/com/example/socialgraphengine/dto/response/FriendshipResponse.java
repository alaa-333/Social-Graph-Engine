package com.example.socialgraphengine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response DTO for friendship data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipResponse {

    private Long id;
    private Long accountId;
    private Long friendId;
    private boolean mutual;
    private LocalDateTime createdAt;
}
