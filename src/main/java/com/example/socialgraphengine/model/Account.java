package com.example.socialgraphengine.model;

import com.example.socialgraphengine.model.enums.AccountJobType;
import com.example.socialgraphengine.model.enums.ProfileType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(exclude = { "user", "posts", "reacts", "comments", "friendRequest", "receivedRequests", "actions", "contacts",
                "friendships", "friendOf", "following", "followers", "notifications", "sentMessages",
                "receivedMessages" })
@EqualsAndHashCode(callSuper = true, exclude = { "user", "posts", "reacts", "comments", "friendRequest",
                "receivedRequests", "actions", "contacts", "friendships", "friendOf", "following", "followers",
                "notifications", "sentMessages", "receivedMessages" })
@Table(name = "accounts", indexes = {
                @Index(name = "idx_profile_type", columnList = "profile_type"),
                @Index(name = "idx_job_type", columnList = "job_type"),
                @Index(name = "idx_user_id", columnList = "user_id")
})
public class Account extends BaseEntity {

        // Link to User (one account belongs to one user)
        @OneToOne
        @JoinColumn(name = "user_id", nullable = false, unique = true)
        private User user;

        @Enumerated(EnumType.STRING)
        @Column(name = "profile_type")
        private ProfileType profileType;

        @Enumerated(EnumType.STRING)
        @Column(name = "job_type")
        private AccountJobType job;

        @Column(name = "profile_picture_url")
        private String profilePictureUrl;

        @Column(name = "background_picture_url")
        private String backgroundPictureUrl;

        @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @Builder.Default
        private List<Posts> posts = new ArrayList<>();

        @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
        @Builder.Default
        private List<Reacts> reacts = new ArrayList<>();

        @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
        @Builder.Default
        private List<Comments> comments = new ArrayList<>();

        @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
        @Builder.Default
        private List<FriendRequest> friendRequest = new ArrayList<>();

        @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
        @Builder.Default
        private List<FriendRequest> receivedRequests = new ArrayList<>();

        @OneToMany(mappedBy = "account")
        @Builder.Default
        private List<Action> actions = new ArrayList<>();

        @OneToOne(mappedBy = "account")
        private AccountDetails accountDetails;

        @OneToMany(mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
        @Builder.Default
        private Set<Contact> contacts = new HashSet<>();

        // Friendships where this account is the initiator
        @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
        @Builder.Default
        private List<Friendship> friendships = new ArrayList<>();

        // Friendships where this account is the friend
        @OneToMany(mappedBy = "friend", fetch = FetchType.LAZY)
        @Builder.Default
        private List<Friendship> friendOf = new ArrayList<>();

        // Accounts that this account follows
        @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY)
        @Builder.Default
        private List<Follow> following = new ArrayList<>();

        // Accounts that follow this account
        @OneToMany(mappedBy = "following", fetch = FetchType.LAZY)
        @Builder.Default
        private List<Follow> followers = new ArrayList<>();

        // Notifications for this account
        @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
        @Builder.Default
        private List<Notification> notifications = new ArrayList<>();

        // Messages sent by this account
        @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
        @Builder.Default
        private List<Message> sentMessages = new ArrayList<>();

        // Messages received by this account
        @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
        @Builder.Default
        private List<Message> receivedMessages = new ArrayList<>();
}
