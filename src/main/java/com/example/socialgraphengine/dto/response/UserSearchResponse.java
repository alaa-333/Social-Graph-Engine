package com.example.socialgraphengine.dto.response;

import lombok.Builder;

@Builder
public record UserSearchResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String profilePictureUrl,
        String headline) {
}
