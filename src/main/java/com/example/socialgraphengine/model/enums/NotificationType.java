package com.example.socialgraphengine.model.enums;

public enum NotificationType {
    // Friend notifications
    FRIEND_REQUEST_RECEIVED,
    FRIEND_REQUEST_ACCEPTED,
    FRIEND_REQUEST_REJECTED,

    // Follow notifications
    NEW_FOLLOWER,

    // Post notifications
    POST_LIKED,
    POST_COMMENTED,
    POST_SHARED,

    // Comment notifications
    COMMENT_REPLY,
    COMMENT_LIKED,

    // Mention notifications
    MENTIONED_IN_POST,
    MENTIONED_IN_COMMENT,

    // System notifications
    ACCOUNT_VERIFIED,
    SECURITY_ALERT,
    SYSTEM_ANNOUNCEMENT
}
