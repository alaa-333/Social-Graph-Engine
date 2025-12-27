package com.example.socialgraphengine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Contact extends BaseEntity {

    private String name;
    private String email;
    private String phone;
    private String message;

    @ManyToOne
    private Account account;
}
