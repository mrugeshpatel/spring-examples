package com.elance.job.dto;

import com.elance.job.helper.Status;
import com.elance.job.model.Job;
import com.elance.job.model.Profile;

import java.time.LocalDate;

public class JobApplicantDto {

    private JobDto job;
    private ProfileDto profile;
    private LocalDate applicationDate;
    private Status status;
}
