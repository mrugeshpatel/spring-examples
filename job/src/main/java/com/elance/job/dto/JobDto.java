package com.elance.job.dto;

import com.elance.job.helper.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Title cannot be null")
    private String title;
    private String description;
    @NotNull(message = "Rate cannot be null")
    @Min(value = 0, message = "Rate must be greater than or equal to 0")
    private Double rate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedOn;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedOn;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Set<ProfileDto> applicants = new HashSet<>();
}
