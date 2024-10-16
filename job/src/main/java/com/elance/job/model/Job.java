package com.elance.job.model;

import com.elance.job.helper.Status;
import com.elance.job.model.Profile;
import com.elance.job.helper.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Version
    private Long version;
    private String title;
    private String description;
    private Double rate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedOn;
    // LocalDateTime
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedOn;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "job",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<JobApplicant> applicants = new HashSet<>();

   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "applicant",
            joinColumns = @JoinColumn(name = "jobId"),
            inverseJoinColumns = @JoinColumn(name ="profileId")
    )
    private Set<Profile> applicants = new HashSet<>();*/

}
