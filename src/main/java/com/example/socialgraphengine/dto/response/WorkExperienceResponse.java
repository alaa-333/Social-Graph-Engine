package com.example.socialgraphengine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for work experience data.
 * Used when returning work experience information to clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceResponse {

    private Long id;
    private String companyName;
    private String title;
    private String companyLogo;
    private String startDate;
    private String endDate;
    private boolean isPresent;
    private String description;
}
