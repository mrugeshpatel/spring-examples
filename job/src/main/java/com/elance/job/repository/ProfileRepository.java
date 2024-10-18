package com.elance.job.repository;

import com.elance.job.dto.JobDto;
import com.elance.job.dto.ProfileDto;
import com.elance.job.model.Job;
import com.elance.job.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
