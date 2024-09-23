package com.jokim.sivillage.api.review.like.application;

import com.jokim.sivillage.api.review.like.domain.ReviewLikeList;
import com.jokim.sivillage.api.review.like.dto.ReviewLikeListRequestDto;
import com.jokim.sivillage.api.review.like.infrastructure.ReviewLikeListRepository;
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

}
