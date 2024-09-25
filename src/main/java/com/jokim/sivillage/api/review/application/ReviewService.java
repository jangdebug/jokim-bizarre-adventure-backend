package com.jokim.sivillage.api.review.application;

import com.jokim.sivillage.api.review.dto.in.ReviewRequestDto;
import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto;
import com.jokim.sivillage.api.review.dto.out.ReviewSummaryResponseDto;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    void createReview(ReviewRequestDto reviewRequestDto);
    Page<ReviewResponseDto> getReview(String productCode, Pageable pageable);
    ReviewSummaryResponseDto getReviewSummary(String productCode);
}
