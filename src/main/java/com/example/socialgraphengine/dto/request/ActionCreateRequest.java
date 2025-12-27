package com.example.socialgraphengine.dto.request;

import com.example.socialgraphengine.model.enums.ActionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for logging an action.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionCreateRequest {

    @NotNull(message = "action.type.required")
    private ActionType actionType;

    @Size(max = 5000, message = "details.too.long")
    private String details;

    @Size(max = 100, message = "ip.address.too.long")
    private String ipAddress;

    @Size(max = 500, message = "user.agent.too.long")
    private String userAgent;
}
