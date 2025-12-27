package com.example.socialgraphengine.model;

import com.example.socialgraphengine.model.enums.ReactType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString(exclude = { "account", "post" })
@EqualsAndHashCode(callSuper = true, exclude = { "account", "post" })
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(uniqueConstraints = {
                @UniqueConstraint(columnNames = { "account_id", "post_id" })
})
public class Reacts extends BaseEntity {

        @Enumerated(EnumType.STRING)
        private ReactType reactType;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id")
        private Account account;

        @ManyToOne
        @JoinColumn(name = "post_id")
        private Posts post;
}
