package com.jokim.sivillage.api.wishlist.reviewlike.application;

import com.jokim.sivillage.api.wishlist.reviewlike.infrastructure.ReviewLikeListRepository;
import com.jokim.sivillage.api.wishlist.reviewlike.domain.ReviewLikeList;
import com.jokim.sivillage.api.wishlist.reviewlike.dto.ReviewLikeListRequestDto;
import com.jokim.sivillage.api.wishlist.reviewlike.dto.ReviewLikeListResponseDto;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewLikeListServiceImpl implements ReviewLikeListService {

    private final ReviewLikeListRepository reviewLikeListRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public void addReviewLikeList(ReviewLikeListRequestDto reviewLikeListRequestDto) {

        String uuid = jwtTokenProvider.validateAndGetUserUuid(reviewLikeListRequestDto.getAccessToken());
        Long id = reviewLikeListRepository.findByUuidAndReviewCode(uuid,
            reviewLikeListRequestDto.getReviewCode()).map(ReviewLikeList::getId).orElse(null);

        reviewLikeListRepository.save(reviewLikeListRequestDto.toEntity(id, uuid, true));
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewLikeListResponseDto getReviewLikeListState(String accessToken, String reviewCode) {
        boolean isChecked = reviewLikeListRepository.existsByUuidAndReviewCodeAndIsChecked(
            jwtTokenProvider.validateAndGetUserUuid(accessToken), reviewCode, true);

        return ReviewLikeListResponseDto.toDto(isChecked);
    }

    @Transactional
    @Override
    public void deleteReviewLikeList(ReviewLikeListRequestDto reviewLikeListRequestDto) {   // Soft Delete

        String uuid = jwtTokenProvider.validateAndGetUserUuid(reviewLikeListRequestDto.getAccessToken());
        Long id = reviewLikeListRepository.findByUuidAndReviewCode(
            uuid, reviewLikeListRequestDto.getReviewCode()).map(ReviewLikeList::getId).orElse(null);

        if(id == null) return;
        reviewLikeListRepository.save(reviewLikeListRequestDto.toEntity(id, uuid, false));
    }

}
