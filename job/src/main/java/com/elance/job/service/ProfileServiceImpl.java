package com.elance.job.service;

import com.elance.job.dto.ProfileDto;
import com.elance.job.exception.ProfileNotFoundException;
import com.elance.job.model.Profile;
import com.elance.job.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProfileDto findById(long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(()-> new ProfileNotFoundException("Profile not found with id : " +id));
        return modelMapper.map(profile, ProfileDto.class);
    }

    @Override
    public ProfileDto findByEmail(String email) {
        Profile profile = profileRepository.findByEmail(email).orElseThrow(() -> new ProfileNotFoundException("Profile not found with email : " +email));
        return modelMapper.map(profile, ProfileDto.class);
    }

    @Override
    public List<ProfileDto> findAll() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(profile -> modelMapper.map(profile, ProfileDto.class)).collect(
                Collectors.toList());
    }
}
