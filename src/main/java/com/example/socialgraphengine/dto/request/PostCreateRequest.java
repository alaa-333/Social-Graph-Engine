package com.example.socialgraphengine.dto.request;

import com.example.socialgraphengine.model.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating a new post.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {

    @NotBlank(message = "content.required")
    @Size(max = 5000, message = "content.too.long")
    private String content;

    @Size(max = 500, message = "media.url.too.long")
    private String mediaUrl;

    private MediaType mediaType;
}
