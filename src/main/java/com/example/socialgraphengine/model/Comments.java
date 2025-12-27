package com.example.socialgraphengine.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString(exclude = { "post", "account" })
@EqualsAndHashCode(callSuper = true, exclude = { "post", "account" })
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "comments", indexes = {
        @Index(name = "idx_post_created", columnList = "POST_ID, created_at DESC"),
        @Index(name = "idx_account", columnList = "ACCOUNT_ID")
})
public class Comments extends BaseEntity {

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Posts post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
}
