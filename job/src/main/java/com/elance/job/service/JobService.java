package com.elance.job.service;

import com.elance.job.dto.JobDto;
import com.elance.job.dto.JobPageDto;
import com.elance.job.model.Job;

import java.util.List;
import java.util.Optional;


public interface JobService {
    public JobPageDto listJob(int pageNo, int pageSize);
    public Optional<JobDto> findJob(long jobId);
    public Optional<JobDto> findJobAndApplicants(long jobId);
    public JobDto postJob(JobDto jobDto);
    public boolean existsByIdAndVersion(long id, long version);
    public JobDto updateJob(JobDto jobDto);
}
