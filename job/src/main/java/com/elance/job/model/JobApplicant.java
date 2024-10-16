package com.elance.job.model;

import com.elance.job.helper.Status;
import com.elance.job.model.Job;
import com.elance.job.model.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class JobApplicant {

    @EmbeddedId
    private JobApplicantId id;

    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @MapsId("profileId")
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate applicationDate;

    private Status status;

}
