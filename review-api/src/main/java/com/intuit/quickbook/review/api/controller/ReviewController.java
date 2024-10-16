package com.intuit.quickbook.review.api.controller;

import com.intuit.quickbook.review.api.dto.ReviewDto;
import com.intuit.quickbook.review.api.dto.ReviewResponse;
import com.intuit.quickbook.review.api.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("reviews")
    public ResponseEntity<ReviewResponse> getAllReviews(
            @RequestParam(value = "pageNo", defaultValue="0", required=false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue="10", required=false) int pageSize
    ){
        return ResponseEntity.ok(reviewService.getAllReviews(pageNo, pageSize));
    }

    @GetMapping("review/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable long id ){
        return ResponseEntity.ok().body(reviewService.getReview(id));
    }

    @PostMapping("review")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto ){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(reviewDto));
    }

    @GetMapping("/reviews/top")
    public ResponseEntity<List<ReviewDto>> listReviewsByRating(
            @RequestParam(value = "noOfReviews", defaultValue = "10", required = false) int noOfReviews,
            @RequestParam(value = "direction", defaultValue = "asc", required = false) String direction){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.findTopReviews(noOfReviews,direction));
    }
}
