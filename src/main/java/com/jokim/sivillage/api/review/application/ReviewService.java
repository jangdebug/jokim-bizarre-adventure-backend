package com.jokim.sivillage.api.review.application;

import com.jokim.sivillage.api.review.domain.Review;
import com.jokim.sivillage.api.review.dto.out.CustomerReviewDetailDto;
import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto;
import com.jokim.sivillage.api.review.dto.out.ReviewSummaryResponseDto;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<ReviewResponseDto> getReview(String productCode, Pageable pageable);
    ReviewSummaryResponseDto getReviewSummary(String productCode);
    Integer getProductReviewCount(String productCode);
    Integer getCustomerReviewCount(String accessToken);
    List<CustomerReviewDetailDto> getCustomerReviewDetail(String accessToken);
}
