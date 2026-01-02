package com.example.socialgraphengine.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    @Builder.Default
    private String type = "Bearer";

    private String subject;
    private String accessToken;
    private String refreshToken;
    private String expiration;

    private List<String> roles;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
