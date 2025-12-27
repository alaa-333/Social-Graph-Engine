package com.example.socialgraphengine.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Represents work experience information for an account.
 * Converted from Embeddable to Entity for proper JPA handling.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(exclude = { "accountDetails" })
@EqualsAndHashCode(callSuper = true, exclude = { "accountDetails" })
@Table(name = "work_experiences", indexes = {
        @Index(name = "idx_account_details", columnList = "account_details_id"),
        @Index(name = "idx_company_name", columnList = "company_name")
})
public class WorkExperience extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_details_id")
    private AccountDetails accountDetails;

    @Column(name = "company_name")
    private String companyName;

    private String title;

    @Column(name = "company_logo")
    private String companyLogo;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Builder.Default
    @Column(nullable = false)
    private boolean present = false;

    @Column(columnDefinition = "TEXT")
    private String description;
}
