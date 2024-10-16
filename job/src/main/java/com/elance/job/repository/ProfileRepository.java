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

    public Optional<Profile> findByEmail(String email);

     @Query("SELECT new com.elance.job.dto.ProfileDto(p.id, p.version, p.firstName, p.lastName," +
            "p.email, p.phone, p.address, p.skillSet, p.createdOn, p.updatedOn, null)" +
            "FROM Profile p")
     List<ProfileDto> findAllProfile();

    @Query("SELECT new com.elance.job.dto.ProfileDto(p.id, p.version, p.firstName, p.lastName, " +
            "p.email, p.phone, p.address, p.skillSet, p.createdOn, p.updatedOn, null) " +
            "FROM Profile p WHERE p.id = :id")
    public Optional<JobDto> findProfileById(@Param("id") Long id);

    @Query("SELECT p FROM Profile p JOIN p.applications pa JOIN pa.job j WHERE p.id = :profileId")
    public Optional<Job> findProfileWithApplication(@Param("profileId") Long profileId);

    @Query("SELECT new com.elance.job.dto.ProfileDto(p.id, p.version, p.firstName, p.lastName, " +
            "p.email, p.phone, p.address, p.skillSet, p.createdOn, p.updatedOn, null) " +
            "FROM Profile p WHERE p.id = :id AND p.version = :version")
    public Optional<JobDto> findByIdAndVersion(@Param("id") Long id,
                                               @Param("version") Long version);


}
