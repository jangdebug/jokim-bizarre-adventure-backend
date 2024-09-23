package com.jokim.sivillage.api.bridge.brandmedialist.presentation;

import com.jokim.sivillage.api.bridge.brandmedialist.application.BrandMediaListService;
import com.jokim.sivillage.api.bridge.brandmedialist.domain.BrandMediaList;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.BrandMediaListRequestDto;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.BrandMediaListResponseDto;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.AddBrandMediaListRequestVo;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.GetBrandMediaListResponseVo;
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

    @Operation(summary = "Brand-Media-List 조회 API")
    @GetMapping("/{brandCode}")
    public BaseResponse<List<GetBrandMediaListResponseVo>> getBrandMediaList(
        @PathVariable String brandCode) {

        return new BaseResponse<>(
            brandMediaListService.getBrandMediaList(brandCode).stream()
                .map(BrandMediaListResponseDto::toVo).toList());
    }

}
