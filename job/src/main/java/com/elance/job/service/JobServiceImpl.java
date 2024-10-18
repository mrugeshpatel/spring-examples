package com.elance.job.service;

import com.elance.job.dto.JobDto;
import com.elance.job.dto.JobPageDto;
import com.elance.job.exception.JobNotFoundException;
import com.elance.job.model.Job;
import com.elance.job.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public JobPageDto listJob(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Job> reviews = jobRepository.findAll(pageable);
        List<Job> listOfJobs = reviews.getContent();
        List<JobDto> content = listOfJobs.stream()
                .map(job -> modelMapper.map(job, JobDto.class)).collect(Collectors.toList());

        JobPageDto jobPage = new JobPageDto();
        jobPage.setContent(content);
        jobPage.setPageSize(reviews.getSize());
        jobPage.setPageNo(reviews.getNumber());
        jobPage.setTotalPages(reviews.getTotalPages());
        jobPage.setTotalElements(reviews.getNumberOfElements());
        jobPage.setLast(reviews.isLast());
        return jobPage;
        /*return jobRepository.stream().map(job -> modelMapper.map(job, JobDto.class))
                .collect(Collectors.toList());*/
    }

    @Override
    public Optional<JobDto> findJob(long id) {
        return jobRepository.findJobById(id);
        /*return jobRepository.findById(id)
                .map(job -> Optional.of(modelMapper.map(job, JobDto.class)))
                .orElse(Optional.empty());*/
    }

    @Override
    public Optional<JobDto> findJobAndApplicants(long jobId) {
        Optional<Job> jobWithApplicant = jobRepository.findJobWithApplicantById(jobId);
        return jobWithApplicant.map(job -> modelMapper.map(job, JobDto.class));
    }
    @Override
    public JobDto postJob(JobDto jobDto) {
        Job job = jobRepository.save(modelMapper.map(jobDto, Job.class));
        return modelMapper.map(job, JobDto.class);
    }

    @Override
    public boolean existsByIdAndVersion(long id, long version) {
        return jobRepository.existsByIdAndVersion(id, version);
    }

    @Override
    public JobDto updateJob(JobDto postOrUpdateJob) {
        Job job = modelMapper.map(postOrUpdateJob, Job.class);
        return modelMapper.map(jobRepository.save(job), JobDto.class);
    }
}
