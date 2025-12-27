package com.example.socialgraphengine.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Represents a direct message between two accounts.
 * This is a simple one-to-one messaging system.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(exclude = { "sender", "receiver" })
@EqualsAndHashCode(callSuper = true, exclude = { "sender", "receiver" })
@Table(name = "messages", indexes = {
        @Index(name = "idx_sender_receiver_created", columnList = "sender_id, receiver_id, created_at DESC"),
        @Index(name = "idx_receiver_read", columnList = "receiver_id, read"),
        @Index(name = "idx_created_at", columnList = "created_at DESC")
})
public class Message extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Account receiver;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder.Default
    @Column(nullable = false)
    private boolean read = false;

    // Optional: for future features
    @Column(name = "attachment_url")
    private String attachmentUrl;
}
