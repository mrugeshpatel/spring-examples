package com.elance.job.controller;

import com.elance.job.dto.JobDto;
import com.elance.job.dto.JobPageDto;
import com.elance.job.exception.JobNotFoundException;
import com.elance.job.service.JobService;
import com.elance.job.model.Job;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/jobs/")
@Validated
public class JobController {

    private static Logger logger = LogManager.getLogger(JobController.class.getName());

    @Autowired
    JobService jobService;

    /*@GetMapping()
    public ResponseEntity<List<JobDto>> listJobs() {
        logger.info("Starting list Job");
        try {
            List<JobDto> jobDtos = jobService.listJob();
            logger.info("list Job complete");
            return ResponseEntity.ok(jobDtos);
        } catch (Exception e) {
            logger.error("Error occurred while executing list job",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/
    @GetMapping()
    public ResponseEntity<JobPageDto> listJobs(
            @RequestParam(value = "pageNo", defaultValue="0", required=false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue="10", required=false) int pageSize
    ){
        logger.info("Starting list Job");
        try {
            JobPageDto jobPageDto = jobService.listJob(pageNo, pageSize);
            logger.info("list Job complete");
            return ResponseEntity.ok(jobPageDto);
        }  catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<JobDto> findJob(@PathVariable Long id){
        logger.info("Starting find job with id {}", id);
        try{
            return jobService.findJob(id).map(jobDto -> ResponseEntity.ok(jobDto))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        } catch (Exception e) {
            logger.error("Error occurred while getting job with id {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> postJob(@Valid @RequestBody JobDto job, BindingResult bindingResult) {
        logger.info("Starting job post");
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
            }
            JobDto newJob = jobService.postJob(job);
            logger.info("Job id {} posted successfully", newJob.getId());
            return ResponseEntity
                    .created(new URI("/jobs/" + newJob.getId()))
                    .eTag(Long.toString(newJob.getVersion()))
                    .body(newJob);
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("{id})")
    public ResponseEntity<?> putJob(@Valid @RequestBody JobDto job,
                                         @PathVariable Long id,
                                         @RequestHeader("If-Match") Long ifMatch,
                                         BindingResult bindingResult) {
        logger.info("Starting job put");
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
            }
            if(jobService.existsByIdAndVersion(id, ifMatch))
                return ResponseEntity.status(HttpStatus.OK).body(jobService.updateJob(job));
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/jobs/{id}/applicants")
    public ResponseEntity<JobDto> getJobAndApplicants(@PathVariable Long id){
        logger.info("Starting get job with application, job id {}", id);
        try{
            Optional<JobDto> jobAndApplicants = jobService.findJobAndApplicants(id);
            return jobAndApplicants.map(jobDto -> ResponseEntity.ok(jobDto))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        }catch (Exception e) {
            logger.error("Error occurred while getting job with id {}", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
