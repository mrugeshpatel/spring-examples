package com.elance.job.service;

import com.elance.job.dto.JobDto;
import com.elance.job.dto.ProfileDto;
import com.elance.job.exception.ProfileNotFoundException;
import com.elance.job.model.Job;
import com.elance.job.model.Profile;
import com.elance.job.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProfileDto create(ProfileDto profileDto) {
        Profile profile = profileRepository.save(modelMapper.map(profileDto, Profile.class));
        return modelMapper.map(profile, ProfileDto.class);
    }
}
