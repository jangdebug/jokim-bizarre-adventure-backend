package com.jokim.sivillage.api.bridge.reviewmedialist.application;

import com.jokim.sivillage.api.bridge.reviewmedialist.domain.ReviewMediaList;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.ReviewMediaListRequestDto;

public interface ReviewMediaListService {

    void addReviewMediaList(ReviewMediaListRequestDto reviewMediaListRequestDto);

}
