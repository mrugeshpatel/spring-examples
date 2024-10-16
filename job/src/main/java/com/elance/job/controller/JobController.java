package com.elance.job.controller;

import com.elance.job.dto.JobDto;
import com.elance.job.dto.JobPageDto;
import com.elance.job.exception.JobNotFoundException;
import com.elance.job.service.JobService;
import com.elance.job.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/jobs/")
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
    public ResponseEntity<JobDto> getJob(@PathVariable Long id){
        logger.info("Starting get job with id {}", id);
        try{
            JobDto jobDto = jobService.getJob(id);
            return ResponseEntity.ok(jobDto);
        }catch (JobNotFoundException ex) {
            logger.info(ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error occurred while getting job with id {}", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("job")
    public ResponseEntity<JobDto> postJob(@RequestBody JobDto job) {
        logger.info("Starting job post");
        try {
            JobDto newJob = jobService.postJob(job);
            logger.info("Job id {} posted successfully", newJob.getId());
            return ResponseEntity
                    .created(new URI("/jobs/" + newJob.getId()))
                    .eTag(Long.toString(newJob.getVersion()))
                    .body(newJob);
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("job")
    public ResponseEntity<JobDto> putJob(@RequestBody JobDto job) {
        logger.info("Starting job put");
        try {
            if(job.getId() == null ||  job.getVersion() == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            else if(jobService.findByIdAndVersion(job.getId(), job.getVersion())
                    .isPresent())
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            else
                return ResponseEntity.status(HttpStatus.OK).body(jobService.updateJob(job));
        } catch (Exception e) {
            logger.error("Starting get job with id {}", job.getId(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/jobs/{id}/applicants")
    public ResponseEntity<JobDto> getJobAndApplicants(@PathVariable Long id){
        logger.info("Starting get job with application,  job id {}", id);
        try{
            Optional<JobDto> job = jobService.getJob(id);
            if(job.isPresent())
                return ResponseEntity.ok(jobService.getJob(id).get());

            logger.info("There is no job with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            logger.error("Error occurred while getting job with id {}", id);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
