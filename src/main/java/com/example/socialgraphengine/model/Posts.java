package com.example.socialgraphengine.model;

import com.example.socialgraphengine.model.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = { "account", "comments", "reacts" })
@EqualsAndHashCode(callSuper = true, exclude = { "account", "comments", "reacts" })
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "posts", indexes = {
        @Index(name = "idx_account_created", columnList = "account_id, created_at DESC"),
        @Index(name = "idx_deleted", columnList = "deleted"),
        @Index(name = "idx_media_type", columnList = "media_type")
})
public class Posts extends BaseEntity {

    @Lob
    @Column(columnDefinition = "CLOB")
    private String content;

    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Comments> comments = new HashSet<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Reacts> reacts = new HashSet<>();

    // Count fields for performance (avoid loading collections just to count)
    @Column(name = "comments_count", nullable = false)
    @Builder.Default
    private Long commentsCount = 0L;

    @Column(name = "reacts_count", nullable = false)
    @Builder.Default
    private Long reactsCount = 0L;
}
