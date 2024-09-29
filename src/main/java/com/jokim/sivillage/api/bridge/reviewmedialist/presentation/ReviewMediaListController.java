package com.jokim.sivillage.api.bridge.reviewmedialist.presentation;

import com.jokim.sivillage.api.bridge.reviewmedialist.application.ReviewMediaListService;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.in.ReviewMediaListRequestDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.AllReviewMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.vo.in.AddReviewMediaListRequestVo;
import com.jokim.sivillage.api.bridge.reviewmedialist.vo.out.GetAllReviewMediaListsResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "개별 리뷰의 Review-Media-List 전체 조회 API", description = "Media 테이블과 RightJoin 하여 Id 오름차순 조회")
    @GetMapping("/{reviewCode}")
    public BaseResponse<List<GetAllReviewMediaListsResponseVo>> getAllReviewMediaLists(
        @PathVariable String reviewCode) {

        return new BaseResponse<>(reviewMediaListService.getAllReviewMediaLists(reviewCode)
            .stream().map(AllReviewMediaListsResponseDto::toVo).toList());
    }

    @Operation(summary = "상품의 Review-Media-List 전체 조회 API", description = "Media 테이블과 RightJoin 하여 Id 내림차순 조회")
    @GetMapping("/product/{productCode}")
    public BaseResponse<List<GetAllReviewMediaListsResponseVo>> getAllReviewMediaListsByProduct(
            @PathVariable String productCode,
            @RequestParam(required = false) Integer fetchLimit) {

        return new BaseResponse<>(reviewMediaListService.getAllReviewMediaListsByProduct(productCode,
                        fetchLimit).stream().map(AllReviewMediaListsResponseDto::toVo).toList());
    }

}
