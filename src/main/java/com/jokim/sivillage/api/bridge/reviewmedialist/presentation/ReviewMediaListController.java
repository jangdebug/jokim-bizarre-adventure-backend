package com.jokim.sivillage.api.bridge.reviewmedialist.presentation;

import com.jokim.sivillage.api.bridge.reviewmedialist.application.ReviewMediaListService;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.ReviewMediaListRequestDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.vo.AddReviewMediaListRequestVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bridge")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/bridge/review-media-list")
public class ReviewMediaListController {

    private final ReviewMediaListService reviewMediaListService;

    @Operation(summary = "Review-Media-List 생성 API")
    @PostMapping
    public BaseResponse<Void> addReviewMediaList(
        @RequestBody AddReviewMediaListRequestVo addReviewMediaListRequestVo) {

        reviewMediaListService.addReviewMediaList(
            ReviewMediaListRequestDto.toDto(addReviewMediaListRequestVo));
        return new BaseResponse<>();
    }

}
