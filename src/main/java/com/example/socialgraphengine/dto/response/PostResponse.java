package com.example.socialgraphengine.dto.response;

import com.example.socialgraphengine.model.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Response DTO for post data.
 * Includes post content, media, author information, and engagement data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Long id;
    private String content;
    private String mediaUrl;
    private MediaType mediaType;
    private String accountProfilePictureUrl;
    private String accountName;
    private Long accountId;
    private String accountEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;
    private Long commentsCount;
    private Long reactsCount;
    private List<ReactResponse> reacts;
    private List<CommentResponse> comments;
}
