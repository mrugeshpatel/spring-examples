package com.intuit.quickbook.review.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.quickbook.review.api.dto.ReviewDto;
import com.intuit.quickbook.review.api.dto.ReviewResponse;
import com.intuit.quickbook.review.api.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ReviewController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ReviewService service;

    @Test
    public void testGetAllReviews() throws Exception {
        ReviewResponse mockResponse = createReviewResponse();
        doReturn(mockResponse).when(service).getAllReviews(anyInt(),anyInt());
        mvc.perform(get("/api/reviews")
                .param("pageNo", "0")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content").exists());
    }

    @Test
    public void testGetReviewById() throws Exception {
        ReviewDto mockReview = createReviewDto();
        doReturn(mockReview).when(service).getReview(anyLong());
        mvc.perform(get("/api/review/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.book", is("test1")));
    }

    @Test
    public void testSaveReview() throws Exception {
        ReviewDto mockReview = createReviewDto();
        doReturn(mockReview).when(service).createReview(mockReview);
        mvc.perform(MockMvcRequestBuilders.post("/api/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockReview)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.book", is("test1")))
                .andExpect(jsonPath("$.id").exists());
    }

    private ReviewResponse createReviewResponse() throws Exception {
        List<ReviewDto> list = new ArrayList<>();
        ReviewDto reviewDto = ReviewDto.builder().id(1).book("test1").build();
        list.add(reviewDto);
        reviewDto = ReviewDto.builder().id(2).book("test2").build();
        list.add(reviewDto);
        return ReviewResponse.builder().pageNo(1).pageSize(10).content(list).build();
    }

    private ReviewDto createReviewDto() throws Exception {
        return ReviewDto.builder().id(1).book("test1").build();

    }



}
