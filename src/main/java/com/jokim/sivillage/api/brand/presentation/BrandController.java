package com.jokim.sivillage.api.brand.presentation;

import com.jokim.sivillage.api.brand.application.BrandService;
import com.jokim.sivillage.api.brand.dto.BrandRequestDto;
import com.jokim.sivillage.api.brand.dto.BrandResponseDto;
import com.jokim.sivillage.api.brand.vo.in.AddBrandRequestVo;
import com.jokim.sivillage.api.brand.vo.in.UpdateBrandRequestVo;
import com.jokim.sivillage.api.brand.vo.out.BrandDetailResponseVo;
import com.jokim.sivillage.api.brand.vo.out.BrandSummaryResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Brand 전체 조회 API")
    @GetMapping
    public BaseResponse<List<BrandDetailResponseVo>> getAllBrands() {

        return new BaseResponse<>(brandService.getAllBrands().stream()
                .map(BrandResponseDto::toDetailVo).toList());
    }

    @Operation(summary = "Brand summary 조회 API")
    @GetMapping("/{brandCode}")
    public BaseResponse<BrandSummaryResponseVo> getBrandSummary(@PathVariable String brandCode) {

        return new BaseResponse<>(brandService.getBrandSummary(brandCode).toSummaryVo());
    }

    @Operation(summary = "Brand 정보 수정 API")
    @PutMapping
    public BaseResponse<Void> updateBrand(@RequestBody UpdateBrandRequestVo updateBrandRequestVo) {

        brandService.updateBrand(BrandRequestDto.toDto(updateBrandRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "Brand 삭제 API")
    @DeleteMapping("/{brandCode}")
    public BaseResponse<Void> deleteBrand(@PathVariable String brandCode) {

        brandService.deleteBrand(brandCode);
        return new BaseResponse<>();
    }

}
