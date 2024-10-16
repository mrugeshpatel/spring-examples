package com.intuit.quickbook.review.api.service;

import com.intuit.quickbook.review.api.dto.ReviewDto;
import com.intuit.quickbook.review.api.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto getReview(long reviewId);
    ReviewResponse getAllReviews(int pageNo, int pageSize);
    List<ReviewDto> findTopReviews(int noOfReviews, String direction);
}
