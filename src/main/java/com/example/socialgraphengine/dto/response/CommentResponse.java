package com.example.socialgraphengine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response DTO for comment data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long id;
    private String text;
    private Long postId;
    private Long accountId;
    private String profilePictureUrl;
    private String accountName;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
