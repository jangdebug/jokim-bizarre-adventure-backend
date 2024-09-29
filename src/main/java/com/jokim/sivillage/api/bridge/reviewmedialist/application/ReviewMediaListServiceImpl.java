package com.jokim.sivillage.api.bridge.reviewmedialist.application;

import com.jokim.sivillage.api.bridge.reviewmedialist.dto.in.ReviewMediaListRequestDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.AllReviewMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure.ReviewMediaListRepository;
import com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure.ReviewMediaListRepositoryCustom;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewMediaListServiceImpl implements ReviewMediaListService {

    private final ReviewMediaListRepository reviewMediaListRepository;
    private final ReviewMediaListRepositoryCustom reviewMediaListRepositoryCustom;

    @Transactional
    @Override
    public void addReviewMediaList(ReviewMediaListRequestDto reviewMediaListRequestDto) {

        reviewMediaListRepository.save(reviewMediaListRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public List<AllReviewMediaListsResponseDto> getAllReviewMediaLists(String reviewCode) {
        return reviewMediaListRepositoryCustom.getAllReviewMediaLists(reviewCode);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AllReviewMediaListsResponseDto> getAllReviewMediaListsByProduct(String productCode, Integer fetchLimit) {
        return reviewMediaListRepositoryCustom.getAllReviewMediaListsByProduct(productCode, fetchLimit);
    }

}
