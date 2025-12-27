package com.example.socialgraphengine.dto.response;

import com.example.socialgraphengine.model.enums.AccountJobType;
import com.example.socialgraphengine.model.enums.ProfileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response DTO for account data.
 * Contains account profile information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private Long id;
    private Long userId;
    private ProfileType profileType;
    private AccountJobType job;
    private String profilePictureUrl;
    private String backgroundPictureUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;
}
