package com.example.socialgraphengine.model;

import com.example.socialgraphengine.model.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString(exclude = { "sender", "receiver" })
@EqualsAndHashCode(callSuper = true, exclude = { "sender", "receiver" })
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "friend_requests", indexes = {
        @Index(name = "idx_receiver_status", columnList = "receiver_id, status"),
        @Index(name = "idx_sender_status", columnList = "sender_id, status"),
        @Index(name = "idx_status_read", columnList = "status, read")
})
public class FriendRequest extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account receiver;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RequestStatus status = RequestStatus.PENDING;

    @Builder.Default
    boolean read = false;
}
