package com.elance.job.service;

import com.elance.job.dto.JobDto;
import com.elance.job.model.Job;
import com.elance.job.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<JobDto> listJob() {
        return jobRepository.findAllJob();
        /*return jobRepository.stream().map(job -> modelMapper.map(job, JobDto.class))
                .collect(Collectors.toList());*/
    }

    @Override
    public Optional<JobDto> getJob(long id) {
        return jobRepository.findJobById(id);
        /*return jobRepository.findById(id)
                .map(job -> Optional.of(modelMapper.map(job, JobDto.class)))
                .orElse(Optional.empty());*/
    }

    @Override
    public Optional<JobDto> getJobAndApplicants(long jobId) {
        return Optional.of(modelMapper.map(jobRepository.findJobWithApplicantById(jobId),
                JobDto.class));
    }
    @Override
    public JobDto postJob(JobDto jobDto) {
        Job job = jobRepository.save(modelMapper.map(jobDto, Job.class));
        return modelMapper.map(job, JobDto.class);
    }

    @Override
    public Optional<JobDto> findByIdAndVersion(long id, long version) {
        return jobRepository.findByIdAndVersion(id, version);
    }

    @Override
    public JobDto updateJob(JobDto postOrUpdateJob) {
        Job job = modelMapper.map(postOrUpdateJob, Job.class);
        return modelMapper.map(jobRepository.save(job), JobDto.class);
    }
}
