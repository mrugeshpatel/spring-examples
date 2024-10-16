package com.intuit.quickbook.review.api.repository;

import com.intuit.quickbook.review.api.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    //@Query(value = "select r from Review r order by r.rating ?2 limit ?1", nativeQuery = true)
    @Query(value = "select * from review r order by " +
            "CASE WHEN ?2 = 'asc' THEN r.rating END ASC, " +
            "CASE WHEN ?2 = 'desc' THEN r.rating END DESC" +
            " limit ?1",nativeQuery = true)
    public List<Review> findTopReviews(int noOfReviews, String direction);
}
