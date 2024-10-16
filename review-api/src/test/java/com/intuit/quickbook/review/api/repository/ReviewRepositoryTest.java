package com.intuit.quickbook.review.api.repository;

import com.intuit.quickbook.review.api.model.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testCreateView() {
        Review review = getReview();
        Review dbReview = reviewRepository.save(review);
        Assertions.assertThat(dbReview).isNotNull();
        Assertions.assertThat(dbReview.getAuthor()).isEqualTo("auth2");
        Assertions.assertThat(dbReview.getDescription()).isEqualTo("test description");
        Assertions.assertThat(dbReview.getRating()).isEqualTo(5);
    }

    @Test
    public void testFindById() {
        Review review = getReview();
        Review saved = reviewRepository.save(review);
        Optional<Review> r1 = reviewRepository.findById(1l);
        Assertions.assertThat(r1.isPresent()).isTrue();
    }

    @Test
    public void testUpdateReview() {
        Review review = getReview();
        Review saved1 = reviewRepository.save(review);
        saved1.setRating(4);
        Review saved2 = reviewRepository.save(saved1);
        Assertions.assertThat(saved2.getRating()).isEqualTo(4);
    }

    private Review getReview() {
        return Review.builder().author("auth2").description("test description").rating(5).build();
    }
}
