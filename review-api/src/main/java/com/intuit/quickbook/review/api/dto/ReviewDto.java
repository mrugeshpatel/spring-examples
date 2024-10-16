package com.intuit.quickbook.review.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private long id;
    private String book;
    private String description;
    private String author;
    private int rating;
}
