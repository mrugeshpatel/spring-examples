package com.elance.job.model;

import jakarta.persistence.Access;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class JobApplicantId implements Serializable {

    private Long jobId;
    private Long profileId;

}
