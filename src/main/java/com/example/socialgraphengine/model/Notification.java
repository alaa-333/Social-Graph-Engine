package com.example.socialgraphengine.model;

import com.example.socialgraphengine.model.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Represents a notification sent to an account.
 * Notifications inform users about various events in the system.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(exclude = { "account" })
@EqualsAndHashCode(callSuper = true, exclude = { "account" })
@Table(name = "notifications", indexes = {
        @Index(name = "idx_account_read", columnList = "account_id, read"),
        @Index(name = "idx_created_at", columnList = "created_at DESC"),
        @Index(name = "idx_type", columnList = "type")
})
public class Notification extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Column(nullable = false, length = 500)
    private String message;

    private String link; // URL to the related resource

    @Builder.Default
    @Column(nullable = false)
    private boolean read = false;
}
