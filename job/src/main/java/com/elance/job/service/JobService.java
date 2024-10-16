package com.elance.job.service;

import com.elance.job.dto.JobDto;
import com.elance.job.model.Job;

import java.util.List;
import java.util.Optional;


public interface JobService {
    public List<JobDto> listJob();
    public Optional<JobDto> getJob(long jobId);
    public Optional<JobDto> getJobAndApplicants(long jobId);
    public JobDto postJob(JobDto jobDto);
    public Optional<JobDto> findByIdAndVersion(long id, long version);
    public JobDto updateJob(JobDto jobDto);
}
