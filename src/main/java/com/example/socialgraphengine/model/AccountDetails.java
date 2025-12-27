package com.example.socialgraphengine.model;

import com.example.socialgraphengine.model.enums.Language;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Account_Details")

public class AccountDetails extends BaseEntity {

    @Embedded
    private PersonalInfo personalInfo;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "accountDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<WorkExperience> workExperiences = new ArrayList<>();

    @ElementCollection(targetClass = Language.class)
    @CollectionTable(name = "account_languages", joinColumns = @JoinColumn(name = "account_id"))
    @Enumerated(EnumType.STRING)
    private Set<Language> languages = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}

// Embeddable value objects

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class PersonalInfo {
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dateOfBirth;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class Address {
    private String street;
    private String city;
    private String state;
    private String country;
}
