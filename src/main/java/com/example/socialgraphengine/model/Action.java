package com.example.socialgraphengine.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(exclude = { "account" })
@EqualsAndHashCode(callSuper = true, exclude = { "account" })
@Table(name = "actions", indexes = {
        @Index(name = "idx_account_action_type", columnList = "account_id, action_type"),
        @Index(name = "idx_created_at", columnList = "created_at DESC"),
        @Index(name = "idx_action_type", columnList = "action_type")
})
public class Action extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", nullable = false)
    private com.example.socialgraphengine.model.enums.ActionType actionType;

    @Column(columnDefinition = "TEXT")
    private String details; // JSON or description of the action

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;
}
