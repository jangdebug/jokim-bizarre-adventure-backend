package com.jokim.sivillage.api.wishlist.reviewlike.presentation;

import static com.jokim.sivillage.common.utils.TokenExtractor.extractToken;

import com.jokim.sivillage.api.wishlist.reviewlike.application.ReviewLikeListService;
import com.jokim.sivillage.api.wishlist.reviewlike.dto.ReviewLikeListRequestDto;
import com.jokim.sivillage.api.wishlist.reviewlike.vo.AddReviewLikeListRequestVo;
import com.jokim.sivillage.api.wishlist.reviewlike.vo.GetReviewLikeListResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Review-Like")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/review/like")
public class ReviewLikeListController {

    private final ReviewLikeListService reviewLikeListService;

    @Operation(summary = "Review Like List 생성 API")
    @PostMapping
    public BaseResponse<Void> addReviewLikeList(
        @RequestHeader("Authorization") String authorizationHeader,
        @RequestBody AddReviewLikeListRequestVo addReviewLikeListRequestVo) {

        reviewLikeListService.addReviewLikeList(ReviewLikeListRequestDto.toDto(
            addReviewLikeListRequestVo, extractToken(authorizationHeader)));
        return new BaseResponse<>();
    }

    @Operation(summary = "Review Like List 상태 조회 API")
    @GetMapping("/{reviewCode}")
    public BaseResponse<GetReviewLikeListResponseVo> getReviewLikeListState(
        @RequestHeader("Authorization") String authorizationHeader, @PathVariable String reviewCode) {

        return new BaseResponse<>(reviewLikeListService.getReviewLikeListState(
            extractToken(authorizationHeader), reviewCode).toVo());
    }

    @Operation(summary = "Review Like List 삭제 API")
    @DeleteMapping("/{reviewCode}")
    public BaseResponse<Void> deleteReviewLikeList(
        @RequestHeader("Authorization") String authorizationHeader, @PathVariable String reviewCode) {

        reviewLikeListService.deleteReviewLikeList(ReviewLikeListRequestDto.toDto(
            extractToken(authorizationHeader), reviewCode));
        return new BaseResponse<>();
    }

}
