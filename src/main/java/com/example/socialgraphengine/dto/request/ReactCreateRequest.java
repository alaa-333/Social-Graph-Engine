package com.example.socialgraphengine.dto.request;

import com.example.socialgraphengine.model.enums.ReactType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating or updating a reaction on a post.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReactCreateRequest {

    @NotNull(message = "react.type.required")
    private ReactType reactType;

    @NotNull(message = "post.id.required")
    private Long postId;
}
