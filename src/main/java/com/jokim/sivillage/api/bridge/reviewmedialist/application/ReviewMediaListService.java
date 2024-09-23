package com.jokim.sivillage.api.bridge.reviewmedialist.application;

import com.jokim.sivillage.api.bridge.reviewmedialist.dto.in.ReviewMediaListRequestDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.AllReviewMediaListResponseDto;
import java.util.List;

public interface ReviewMediaListService {

    void addReviewMediaList(ReviewMediaListRequestDto reviewMediaListRequestDto);

    List<AllReviewMediaListResponseDto> getAllReviewMediaLists(String reviewCode);

}
