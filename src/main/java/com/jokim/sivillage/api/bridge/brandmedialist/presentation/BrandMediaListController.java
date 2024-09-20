package com.jokim.sivillage.api.bridge.brandmedialist.presentation;

import com.jokim.sivillage.api.bridge.brandmedialist.application.BrandMediaListService;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.BrandMediaListRequestDto;
import com.jokim.sivillage.api.bridge.brandmedialist.vo.AddBrandMediaListRequestVo;
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

}
