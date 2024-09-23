package com.jokim.sivillage.api.bridge.reviewmedialist.application;

import com.jokim.sivillage.api.bridge.reviewmedialist.dto.ReviewMediaListRequestDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.ReviewMediaListResponseDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure.ReviewMediaListRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewMediaListServiceImpl implements ReviewMediaListService {

    private final ReviewMediaListRepository reviewMediaListRepository;

    @Transactional
    @Override
    public void addReviewMediaList(ReviewMediaListRequestDto reviewMediaListRequestDto) {

        reviewMediaListRepository.save(reviewMediaListRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReviewMediaListResponseDto> getReviewMediaList(String reviewCode) {
        return reviewMediaListRepository.findByReviewCodeOrderById(reviewCode)
            .stream().map(ReviewMediaListResponseDto::toDto).toList();
    }

}
