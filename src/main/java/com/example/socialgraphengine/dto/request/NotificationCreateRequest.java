package com.example.socialgraphengine.dto.request;

import com.example.socialgraphengine.model.enums.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating a notification.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreateRequest {

    @NotNull(message = "account.id.required")
    private Long accountId;

    @NotNull(message = "notification.type.required")
    private NotificationType type;

    @NotBlank(message = "message.required")
    @Size(max = 500, message = "message.too.long")
    private String message;

    @Size(max = 500, message = "link.too.long")
    private String link;
}
