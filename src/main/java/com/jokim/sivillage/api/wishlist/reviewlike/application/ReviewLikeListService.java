package com.jokim.sivillage.api.wishlist.reviewlike.application;

import com.jokim.sivillage.api.wishlist.reviewlike.dto.ReviewLikeListRequestDto;
import com.jokim.sivillage.api.wishlist.reviewlike.dto.ReviewLikeListResponseDto;

public interface ReviewLikeListService {

    void addReviewLikeList(ReviewLikeListRequestDto reviewLikeListRequestDto);

    ReviewLikeListResponseDto getReviewLikeListState(String accessToken, String reviewCode);

    void deleteReviewLikeList(ReviewLikeListRequestDto reviewLikeListRequestDto);

}
