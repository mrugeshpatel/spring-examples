package com.intuit.quickbook.review.api.service.impl;

import com.intuit.quickbook.review.api.dto.ReviewDto;
import com.intuit.quickbook.review.api.dto.ReviewResponse;
import com.intuit.quickbook.review.api.exceptions.ReviewNotFoundException;
import com.intuit.quickbook.review.api.model.Review;
import com.intuit.quickbook.review.api.repository.ReviewRepository;
import com.intuit.quickbook.review.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = reviewRepository.save(mapToEntity(reviewDto));
        return mapToDto(review);
    }

    @Override
    public ReviewDto getReview(long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review not found id : " + reviewId));
        return mapToDto(review);
    }

    @Override
    public ReviewResponse getAllReviews(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Review> reviews = reviewRepository.findAll(pageable);
        List<Review> listOfReviews = reviews.getContent();
        List<ReviewDto> content = listOfReviews.stream().map(this::mapToDto)
                .collect(Collectors.toList());

        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setContent(content);
        reviewResponse.setPageSize(reviews.getSize());
        reviewResponse.setPageNo(reviews.getNumber());
        reviewResponse.setTotalPages(reviews.getTotalPages());
        reviewResponse.setTotalElements(reviews.getNumberOfElements());
        reviewResponse.setLast(reviews.isLast());
        return reviewResponse;

    }

    @Override
    public List<ReviewDto> findTopReviews(int noOfReviews, String direction) {
        List<Review> topReviews = reviewRepository.findTopReviews(noOfReviews, direction);
        return topReviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setAuthor(reviewDto.getAuthor());
        review.setBook(reviewDto.getBook());
        review.setDescription(reviewDto.getDescription());
        review.setRating(reviewDto.getRating());
        return review;
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setAuthor(review.getAuthor());
        dto.setBook(review.getBook());
        dto.setDescription(review.getDescription());
        dto.setRating(review.getRating());
        return dto;
    }
}
