package com.example.socialgraphengine.dto.request;

import com.example.socialgraphengine.model.enums.Language;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Request DTO for updating account details.
 * Used when a user updates their profile information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsRequest {

    @Valid
    private PersonalInfoRequest personalInfo;

    @Valid
    private AddressRequest address;

    @Valid
    private List<WorkExperienceRequest> workExperiences;

    private Set<Language> languages;

    /**
     * Nested DTO for personal information.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PersonalInfoRequest {

        @Size(max = 50, message = "first.name.too.long")
        private String firstName;

        @Size(max = 50, message = "last.name.too.long")
        private String lastName;

        @Size(max = 50, message = "middle.name.too.long")
        private String middleName;

        @Past(message = "date.of.birth.must.be.past")
        private LocalDate dateOfBirth;
    }

    /**
     * Nested DTO for address information.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressRequest {

        @Size(max = 100, message = "street.too.long")
        private String street;

        @Size(max = 50, message = "city.too.long")
        private String city;

        @Size(max = 50, message = "state.too.long")
        private String state;

        @Size(max = 50, message = "country.too.long")
        private String country;
    }
}
