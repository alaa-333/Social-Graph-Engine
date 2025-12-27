package com.example.socialgraphengine.dto.response;

import com.example.socialgraphengine.model.enums.ReactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response DTO for reaction data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReactResponse {

    private Long id;
    private ReactType reactType;
    private Long accountId;
    private Long postId;
    private LocalDateTime createdAt;
}
