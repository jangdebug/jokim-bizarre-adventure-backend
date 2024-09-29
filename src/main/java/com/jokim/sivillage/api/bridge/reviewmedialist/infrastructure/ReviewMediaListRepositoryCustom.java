package com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.AllReviewMediaListsResponseDto;
import java.util.List;

public interface ReviewMediaListRepositoryCustom {

    List<AllReviewMediaListsResponseDto> getAllReviewMediaLists(String reviewCode);

    List<AllReviewMediaListsResponseDto> getAllReviewMediaListsByProduct(String productCode, Integer fetchLimit);

}
