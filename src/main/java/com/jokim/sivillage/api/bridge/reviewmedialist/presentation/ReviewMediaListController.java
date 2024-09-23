package com.jokim.sivillage.api.bridge.reviewmedialist.presentation;

import com.jokim.sivillage.api.bridge.reviewmedialist.application.ReviewMediaListService;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.in.ReviewMediaListRequestDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.AllReviewMediaListResponseDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.vo.in.AddReviewMediaListRequestVo;
import com.jokim.sivillage.api.bridge.reviewmedialist.vo.out.GetAllReviewMediaListResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Review-Media")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/review-media")
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

    @Operation(summary = "Review-Media-List 전체 조회 API", description = "Id 오름차순 조회")
    @GetMapping("/{reviewCode}")
    public BaseResponse<List<GetAllReviewMediaListResponseVo>> getAllReviewMediaLists(
        @PathVariable String reviewCode) {

        return new BaseResponse<>(reviewMediaListService.getAllReviewMediaLists(reviewCode)
            .stream().map(AllReviewMediaListResponseDto::toVo).toList());
    }

}
