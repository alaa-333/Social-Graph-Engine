package com.example.socialgraphengine.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Represents a symmetric friendship relationship between two accounts.
 * When two users are friends, the relationship is bidirectional.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(exclude = { "account", "friend" })
@EqualsAndHashCode(callSuper = true, exclude = { "account", "friend" })
@Table(name = "friendships", uniqueConstraints = {
        @UniqueConstraint(name = "uk_account_friend", columnNames = { "account_id", "friend_id" })
}, indexes = {
        @Index(name = "idx_account_friend", columnList = "account_id, friend_id"),
        @Index(name = "idx_friend_account", columnList = "friend_id, account_id"),
        @Index(name = "idx_created_at", columnList = "created_at DESC")
})
public class Friendship extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id", nullable = false)
    private Account friend;

    // Indicates if the friendship is mutual (bidirectional)
    @Builder.Default
    @Column(nullable = false)
    private boolean mutual = true;
}
