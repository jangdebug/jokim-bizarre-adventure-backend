package com.jokim.sivillage.api.review.presentation;

import com.jokim.sivillage.api.review.application.ReviewService;
import com.jokim.sivillage.api.review.domain.Review;
import com.jokim.sivillage.api.review.dto.in.ReviewRequestDto;
import com.jokim.sivillage.api.review.dto.out.CustomerReviewDetailDto;
import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto;
import com.jokim.sivillage.api.review.vo.in.ReviewRequestVo;
import com.jokim.sivillage.api.review.vo.out.CustomerReviewDetailVo;
import com.jokim.sivillage.api.review.vo.out.ReviewResponseVo;
import com.jokim.sivillage.api.review.vo.out.ReviewSummaryResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Review API", description = "Review API 입니다.", tags = {"Review"})
    @GetMapping("/reviews/product/{productCode}/{page}/{size}")
    public BaseResponse<Page<ReviewResponseVo>> getReview(
        @PathVariable String productCode,
        @PathVariable Integer page,
        @PathVariable Integer size
    ) {
        if (size == null || size == 0) {
            size = 10; // 기본값으로 설정할 값
        }
        Pageable pageable = PageRequest.of(page, size);

        // isBest로 정렬된 리뷰 리스트를 가져옴
        Page<ReviewResponseDto> reviewDtos = reviewService.getReview(productCode, pageable);

        // DTO를 VO로 변환하여 Page로 반환
        Page<ReviewResponseVo> reviewVos = reviewDtos.map(ReviewResponseDto::toVo);

        return new BaseResponse<>(reviewVos);
    }

    @Operation(summary = "ReviewSummary API", description = "Review API 입니다.", tags = {"Review"})
    @GetMapping("/reviews/summary/{productCode}")
    public BaseResponse<ReviewSummaryResponseVo> getReviewSummary(@PathVariable String productCode) {
        return new BaseResponse<>(reviewService.getReviewSummary(productCode).toVo());
    }

    @Operation(summary = "ReviewSummary API", description = "상품에 대한 Review 개수 API 입니다.", tags = {"Review"})
    @GetMapping("/reviews/product-review-count/{productCode}")
    public BaseResponse<Integer> getProductReviewCount(@PathVariable String productCode) {

        return new BaseResponse<>(reviewService.getProductReviewCount(productCode));
    }

    @Operation(summary = "ReviewSummary API", description = "회원에 대한 Review 개수 API 입니다.", tags = {"Review"})
    @GetMapping("/reviews/customer-review-count")
    public BaseResponse<Integer> getCustomerReviewCount(
        @RequestHeader("Authorization") String authorizationHeader
    ) {
        String accessToken = authorizationHeader.replace("Bearer ", "");
        return new BaseResponse<>(reviewService.getCustomerReviewCount(accessToken));
    }

    @Operation(summary = "ReviewSummary API", description = "회원이 작성한 리뷰 상세페이지 API 입니다.", tags = {"Review"})
    @GetMapping("/reviews/customer-review")
    public BaseResponse<List<CustomerReviewDetailVo>> getCustomerReview(
        @RequestHeader("Authorization") String authorizationHeader
    ) {
        String accessToken = authorizationHeader.replace("Bearer ", "");
        return new BaseResponse<>(reviewService.getCustomerReviewDetail(accessToken).stream().map(CustomerReviewDetailDto::toVo).toList());
    }
}
