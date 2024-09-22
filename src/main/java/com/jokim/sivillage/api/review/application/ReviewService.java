package com.jokim.sivillage.api.review.application;

import com.jokim.sivillage.api.review.dto.in.ReviewRequestDto;
import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto;
import com.jokim.sivillage.api.review.dto.out.ReviewSummaryResponseDto;

import java.util.List;

public interface ReviewService {
    void createReview(ReviewRequestDto reviewRequestDto);
    List<ReviewResponseDto> getReview(String productCode);
    ReviewSummaryResponseDto getReviewSummary(String productCode);
}
