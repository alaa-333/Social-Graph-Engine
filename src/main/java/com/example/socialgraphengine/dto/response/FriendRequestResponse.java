package com.example.socialgraphengine.dto.response;

import com.example.socialgraphengine.model.enums.RequestStatus;
import com.example.socialgraphengine.dto.request.AccountRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response DTO for friend request data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestResponse {

    private Long id;
    private AccountRequest sender;
    private AccountRequest receiver;
    private RequestStatus status;
    private String accountEmail;
    private boolean read;
    private LocalDateTime createdAt;
}
