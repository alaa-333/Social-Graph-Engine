package com.example.socialgraphengine.dto.response;

import com.example.socialgraphengine.model.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Response DTO for account details.
 * Used when returning account profile information to clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsResponse {

    private Long id;
    private PersonalInfoResponse personalInfo;
    private AddressResponse address;
    private List<WorkExperienceResponse> workExperiences;
    private Set<Language> languages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Nested DTO for personal information response.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PersonalInfoResponse {
        private String firstName;
        private String lastName;
        private String middleName;
        private LocalDate dateOfBirth;
    }

    /**
     * Nested DTO for address response.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressResponse {
        private String street;
        private String city;
        private String state;
        private String country;
    }
}
