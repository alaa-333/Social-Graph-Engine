package com.example.socialgraphengine.dto.request;

import com.example.socialgraphengine.model.enums.AccountJobType;
import com.example.socialgraphengine.model.enums.ProfileType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating or updating an account.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    private ProfileType profileType;

    private AccountJobType job;

    @Size(max = 500, message = "profile.picture.url.too.long")
    private String profilePictureUrl;

    @Size(max = 500, message = "background.picture.url.too.long")
    private String backgroundPictureUrl;
}
