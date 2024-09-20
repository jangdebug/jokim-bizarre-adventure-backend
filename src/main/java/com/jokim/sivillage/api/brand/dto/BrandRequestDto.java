package com.jokim.sivillage.api.brand.dto;

import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.brand.vo.in.AddBrandRequestVo;
import lombok.Builder;

@Builder
public class BrandRequestDto {

    private String englishName;
    private String koreanName;
    private String englishInitial;
    private String koreanInitial;

    public static BrandRequestDto toDto(AddBrandRequestVo addBrandRequestVo) {
        return BrandRequestDto.builder()
            .englishName(addBrandRequestVo.getEnglishName())
            .koreanName(addBrandRequestVo.getKoreanName())
            .englishInitial(addBrandRequestVo.getEnglishInitial())
            .koreanInitial(addBrandRequestVo.getKoreanInitial())
            .build();
    }

    public Brand toEntity(String brandCode) {
        return Brand.builder()
            .brandCode(brandCode)
            .englishName(englishName)
            .koreanName(koreanName)
            .englishInitial(englishInitial)
            .koreanInitial(koreanInitial)
            .build();
    }

}
