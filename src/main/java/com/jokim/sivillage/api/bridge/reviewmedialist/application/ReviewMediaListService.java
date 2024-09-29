package com.jokim.sivillage.api.bridge.reviewmedialist.application;

import com.jokim.sivillage.api.bridge.reviewmedialist.dto.in.ReviewMediaListRequestDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.AllReviewMediaListsResponseDto;
import java.util.List;

public interface ReviewMediaListService {

    void addReviewMediaList(ReviewMediaListRequestDto reviewMediaListRequestDto);

    List<AllReviewMediaListsResponseDto> getAllReviewMediaLists(String reviewCode);
    List<AllReviewMediaListsResponseDto> getAllReviewMediaListsByProduct(String productCode, Integer fetchLimit);

}
