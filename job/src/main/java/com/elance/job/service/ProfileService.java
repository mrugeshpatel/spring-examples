package com.elance.job.service;

import java.util.List;
import java.util.Optional;

import com.elance.job.dto.ProfileDto;
import com.elance.job.dto.ProfileDto;

public interface ProfileService {

    public List<ProfileDto> listProfile();

    public Optional<ProfileDto> getProfile(long profileId);

    public Optional<ProfileDto> getProfileAndApplicantions(long profileId);

    public ProfileDto createProfile(ProfileDto ProfileDto);

    public Optional<ProfileDto> findByIdAndVersion(long id, long version);

    public ProfileDto updateJob(ProfileDto ProfileDto);
}
