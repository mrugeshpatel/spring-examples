package com.intuit.quickbook.review.api.service;

import com.intuit.quickbook.review.api.dto.ReviewDto;
import com.intuit.quickbook.review.api.dto.ReviewResponse;
import com.intuit.quickbook.review.api.model.Review;
import com.intuit.quickbook.review.api.repository.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @MockBean
    private ReviewRepository reviewRepository;

    @Test
    public void testGetReview() {
        Optional<Review> mockReviewOptional = Optional.of(createReview());
        doReturn(mockReviewOptional).when(reviewRepository).findById(any());
        Review mockReview = mockReviewOptional.get();
        ReviewDto dto = reviewService.getReview(mockReview.getId());
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(mockReview.getId(), dto.getId());
    }

    @Test
    public void testGetAllReviews() {
       Page<Review> reviews = Mockito.mock(Page.class);
       doReturn(reviews).when(reviewRepository).findAll(Mockito.any(Pageable.class));
       ReviewResponse reviewResponse = reviewService.getAllReviews(0,10);
       Assertions.assertNotNull(reviewResponse);
    }

    /*
    void testFindAll() {
        // Setup our mock
        Product mockProduct = new Product(1, "Product Name", 10, 1);
        Product mockProduct2 = new Product(2, "Product Name 2", 15, 3);
        doReturn(Arrays.asList(mockProduct, mockProduct2)).when(repository).findAll();

        // Execute the service call
        List<Product> products = service.findAll();

        Assertions.assertEquals(2, products.size(), "findAll should return 2 products");
    }
     */

    @Test
    public void testCreateReview() {
        Review mockReview = createReview();
        ReviewDto mockReviewDto = createReviewDto();
        doReturn(mockReview).when(reviewRepository).save(any());
        ReviewDto review = reviewService.createReview(mockReviewDto);
        Assertions.assertNotNull(review);
        Assertions.assertNotNull(review.getId());
    }

    private Review createReview() {
        return Review.builder().book("test1").description("test desc").author("test auth").rating(5).build();
    }

    private ReviewDto createReviewDto() {
        return ReviewDto.builder().book("test1").description("test desc").author("test auth").rating(5).build();
    }



}
