package com.elance.job.dto;

import com.elance.job.helper.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long version;
    private String title;
    private String description;
    private Double rate;
    //@Schema(type = "string", format = "date", example = "2024-10-13")
    private LocalDate postedOn;
    //@Schema(type = "string", format = "date", example = "2024-10-13")
    private LocalDate updatedOn;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Set<ProfileDto> applicants = new HashSet<>();
}
