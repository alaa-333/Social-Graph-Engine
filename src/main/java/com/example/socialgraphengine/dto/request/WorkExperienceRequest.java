package com.example.socialgraphengine.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for work experience data.
 * Used when creating or updating work experience entries.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceRequest {

    private Long id; // Null for new entries, populated for updates

    @NotBlank(message = "company.name.required")
    @Size(max = 100, message = "company.name.too.long")
    private String companyName;

    @NotBlank(message = "title.required")
    @Size(max = 100, message = "title.too.long")
    private String title;

    @Size(max = 500, message = "company.logo.url.too.long")
    private String companyLogo;

    private String startDate; // Format: YYYY-MM-DD
    private String endDate; // Format: YYYY-MM-DD, null if isPresent is true
    private boolean isPresent;

    @Size(max = 5000, message = "description.too.long")
    private String description;
}
