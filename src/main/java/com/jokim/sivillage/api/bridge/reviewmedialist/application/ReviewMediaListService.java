package com.jokim.sivillage.api.bridge.reviewmedialist.application;

import com.jokim.sivillage.api.bridge.reviewmedialist.domain.ReviewMediaList;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.ReviewMediaListRequestDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.ReviewMediaListResponseDto;
import java.util.List;

public interface ReviewMediaListService {

    void addReviewMediaList(ReviewMediaListRequestDto reviewMediaListRequestDto);

    List<ReviewMediaListResponseDto> getReviewMediaList(String reviewCode);

}
