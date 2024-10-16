package com.elance.job.controller;

import com.elance.job.dto.JobDto;
import com.elance.job.service.JobService;
import com.elance.job.service.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/")
@RestController
public class ProfileController {

    Logger logger = LogManager.getLogger(ProfileController.class.getName());

    @Autowired
    ProfileService profileService;

    @GetMapping("profiles")
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
    }
}
