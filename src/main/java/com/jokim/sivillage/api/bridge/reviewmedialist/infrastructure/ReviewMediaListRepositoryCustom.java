package com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.AllReviewMediaListResponseDto;
import java.util.List;

public interface ReviewMediaListRepositoryCustom {

    List<AllReviewMediaListResponseDto> getAllReviewMediaLists(String reviewCode);

}
