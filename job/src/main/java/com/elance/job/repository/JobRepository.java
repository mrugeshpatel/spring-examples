package com.elance.job.repository;

import com.elance.job.dto.JobDto;
import com.elance.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT new com.elance.job.dto.JobDto(j.id, j.version, j.title, " +
            "j.description, j.rate, j.postedOn, j.updatedOn,j.status, null ) " +
            "FROM Job j")
    List<JobDto> findAllJob();

    @Query("SELECT new com.elance.job.dto.JobDto(j.id, j.version, j.title, " +
            "j.description, j.rate, j.postedOn, j.updatedOn,j.status, null) " +
            "FROM Job j WHERE j.id = :id")
    public Optional<JobDto> findJobById(@Param("id") Long id);

    @Query("SELECT j FROM Job j JOIN j.applicants ja JOIN ja.profile p WHERE j.id = :jobId")
    public Optional<Job> findJobWithApplicantById(@Param("jobId") Long jobId);

    @Query("SELECT new com.elance.job.dto.JobDto(j.id, j.version, j.title, " +
            "j.description, j.rate, j.postedOn, j.updatedOn,j.status, null) " +
            "FROM Job j WHERE j.id = :id AND j.version = :version")
    public Optional<JobDto> findByIdAndVersion(@Param("id") Long id,
                                            @Param("version") Long version);
}
