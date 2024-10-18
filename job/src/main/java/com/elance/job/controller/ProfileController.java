package com.elance.job.controller;

import com.elance.job.dto.JobDto;
import com.elance.job.dto.ProfileDto;
import com.elance.job.service.JobService;
import com.elance.job.service.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/v1/profiles/")
@RestController
public class ProfileController {

    private static Logger logger = LogManager.getLogger(ProfileController.class.getName());

    @Autowired
    ProfileService profileService;

    @PostMapping()
    public ResponseEntity<ProfileDto> postJob(@RequestBody ProfileDto profile) {
        logger.info("Starting create profile");
        try {
            ProfileDto newProfile = profileService.create(profile);
            logger.info("Profile id {} created successfully", newProfile.getId());
            return ResponseEntity
                    .created(new URI("/profiles/" + newProfile.getId()))
                    .eTag(Long.toString(newProfile.getVersion()))
                    .body(newProfile);
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*@GetMapping("")
    public ResponseEntity<List<JobDto>> listProfiles() {
        logger.info("Starting list Profiles");
        try {
            List<JobDto> jobDtos = profileService.
            logger.info("list Job complete");
            return ResponseEntity.ok(jobDtos);
        } catch (Exception e) {
            logger.error("Error occurred while executing list job",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/
}
