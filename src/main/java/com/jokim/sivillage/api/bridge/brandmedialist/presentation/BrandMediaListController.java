package com.jokim.sivillage.api.bridge.brandmedialist.presentation;

import com.jokim.sivillage.api.bridge.brandmedialist.application.BrandMediaListService;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.in.BrandMediaListRequestDto;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.out.AllBrandMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.in.AddBrandMediaListRequestVo;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.in.UpdateBrandMediaListRequestVo;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.out.GetAllBrandMediaListsResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Brand-Media")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/brand-media")
public class BrandMediaListController {

    private final BrandMediaListService brandMediaListService;

    @Operation(summary = "Brand-Media-List 생성 API")
    @PostMapping
    public BaseResponse<Void> addBrandMediaList(
        @RequestBody AddBrandMediaListRequestVo addBrandMediaListRequestVo) {

        brandMediaListService.addBrandMediaList(
            BrandMediaListRequestDto.toDto(addBrandMediaListRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "Brand-Media-List 전체 조회 API", description = "Media 테이블과 RightJoin 하여 Id 오름차순 조회")
    @GetMapping("/{brandCode}")
    public BaseResponse<List<GetAllBrandMediaListsResponseVo>> getAllBrandMediaLists(
        @PathVariable String brandCode) {

        return new BaseResponse<>(
            brandMediaListService.getAllBrandMediaLists(brandCode).stream()
                .map(AllBrandMediaListsResponseDto::toVo).toList());
    }

    @Operation(summary = "Brand-Media-List 로고 수정 API")
    @PutMapping
    public BaseResponse<Void> updateBrandMediaList(
        @RequestBody UpdateBrandMediaListRequestVo updateBrandMediaListRequestVo) {

        brandMediaListService.updateBrandMediaList(BrandMediaListRequestDto.toDto(
            updateBrandMediaListRequestVo));
        return new BaseResponse<>();
    }

}
