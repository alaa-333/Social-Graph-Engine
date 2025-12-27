package com.example.socialgraphengine.dto.response;

import com.example.socialgraphengine.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response DTO for notification data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long id;
    private Long accountId;
    private NotificationType type;
    private String message;
    private String link;
    private boolean read;
    private LocalDateTime createdAt;
}
