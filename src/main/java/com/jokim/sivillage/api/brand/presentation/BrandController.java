package com.jokim.sivillage.api.brand.presentation;

import com.jokim.sivillage.api.brand.application.BrandService;
import com.jokim.sivillage.api.brand.dto.BrandRequestDto;
import com.jokim.sivillage.api.brand.vo.in.AddBrandRequestVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Brand")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/brand")
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "Brand 생성 API")
    @PostMapping
    public BaseResponse<Void> addBrand(@RequestBody AddBrandRequestVo addBrandRequestVo) {
        brandService.addBrand(BrandRequestDto.toDto(addBrandRequestVo));
        return new BaseResponse<>();
    }

}
