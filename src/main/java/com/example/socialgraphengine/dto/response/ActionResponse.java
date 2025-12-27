package com.example.socialgraphengine.dto.response;

import com.example.socialgraphengine.model.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response DTO for action data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionResponse {

    private Long id;
    private Long accountId;
    private ActionType actionType;
    private String details;
    private String ipAddress;
    private String userAgent;
    private LocalDateTime createdAt;
}
