package com.example.socialgraphengine.model.enums;

public enum ActionType {
    // Authentication actions
    LOGIN,
    LOGOUT,
    REGISTER,
    PASSWORD_CHANGED,

    // Post actions
    POST_CREATED,
    POST_UPDATED,
    POST_DELETED,
    POST_VIEWED,

    // Profile actions
    PROFILE_UPDATED,
    PROFILE_PICTURE_CHANGED,
    BACKGROUND_PICTURE_CHANGED,

    // Social actions
    FRIEND_REQUEST_SENT,
    FRIEND_REQUEST_ACCEPTED,
    FRIEND_REQUEST_REJECTED,
    FRIEND_REMOVED,
    ACCOUNT_FOLLOWED,
    ACCOUNT_UNFOLLOWED,

    // Interaction actions
    COMMENT_ADDED,
    COMMENT_UPDATED,
    COMMENT_DELETED,
    REACT_ADDED,
    REACT_REMOVED,

    // Other actions
    ACCOUNT_DETAILS_UPDATED,
    CONTACT_SUBMITTED
}
