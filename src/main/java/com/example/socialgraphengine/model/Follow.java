package com.example.socialgraphengine.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Represents an asymmetric follow relationship between two accounts.
 * One account (follower) follows another account (following).
 * This is a one-way relationship (like Twitter/Instagram).
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(exclude = { "follower", "following" })
@EqualsAndHashCode(callSuper = true, exclude = { "follower", "following" })
@Table(name = "follows", uniqueConstraints = {
        @UniqueConstraint(name = "uk_follower_following", columnNames = { "follower_id", "following_id" })
}, indexes = {
        @Index(name = "idx_follower", columnList = "follower_id, created_at DESC"),
        @Index(name = "idx_following", columnList = "following_id, created_at DESC")
})
public class Follow extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private Account follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    private Account following;
}
