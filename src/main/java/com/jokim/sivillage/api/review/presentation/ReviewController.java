package com.jokim.sivillage.api.review.presentation;

import com.jokim.sivillage.api.review.application.ReviewService;
import com.jokim.sivillage.api.review.dto.in.ReviewRequestDto;
import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto;
import com.jokim.sivillage.api.review.dto.out.ReviewSummaryResponseDto;
import com.jokim.sivillage.api.review.vo.in.ReviewRequestVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Review API", description = "Review API 입니다.", tags = {"Review"})
    @PostMapping("/review")
    public BaseResponse<Void> createReview(
            @RequestBody ReviewRequestVo reviewRequestVo) {
        reviewService.createReview(ReviewRequestDto.toDto(reviewRequestVo));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "Review API", description = "Review API 입니다.", tags = {"Review"})
    @GetMapping("/reviews/product/{productCode}")
    public BaseResponse<List<ReviewResponseDto>> getReview(@PathVariable String productCode) {
        List<ReviewResponseDto> reviewResponses = reviewService.getReview(productCode);
        return new BaseResponse<>(reviewResponses);
    }

    @Operation(summary = "ReviewSummary API", description = "Review API 입니다.", tags = {"Review"})
    @GetMapping("/reviews/summary/{productCode}")
    public BaseResponse<ReviewSummaryResponseDto> getReviewSummary(@PathVariable String productCode) {
        ReviewSummaryResponseDto reviewResponses = reviewService.getReviewSummary(productCode);
        return new BaseResponse<>(reviewResponses);
    }
}
