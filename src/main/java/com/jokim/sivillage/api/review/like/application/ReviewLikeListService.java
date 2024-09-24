package com.jokim.sivillage.api.review.like.application;

import com.jokim.sivillage.api.review.like.dto.ReviewLikeListRequestDto;
import com.jokim.sivillage.api.review.like.dto.ReviewLikeListResponseDto;

public interface ReviewLikeListService {

    void addReviewLikeList(ReviewLikeListRequestDto reviewLikeListRequestDto);

    ReviewLikeListResponseDto getReviewLikeListState(String accessToken, String reviewCode);

    void deleteReviewLikeList(ReviewLikeListRequestDto reviewLikeListRequestDto);

}
