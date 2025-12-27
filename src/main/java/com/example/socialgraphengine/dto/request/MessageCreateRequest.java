package com.example.socialgraphengine.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for sending a message.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreateRequest {

    @NotNull(message = "receiver.id.required")
    private Long receiverId;

    @NotBlank(message = "content.required")
    @Size(max = 5000, message = "content.too.long")
    private String content;

    @Size(max = 500, message = "attachment.url.too.long")
    private String attachmentUrl;
}
