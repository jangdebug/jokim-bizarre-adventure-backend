package com.jokim.sivillage.api.brand.dto.out;

import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.brand.vo.out.GetBrandDetailResponseVo;
import lombok.Builder;

@Builder
public class BrandResponseDetailDto {

    private String brandCode;
    private String englishName;
    private String koreanName;
    private String englishInitial;
    private String koreanInitial;

    public static BrandResponseDetailDto toDto(Brand brand) {
        return BrandResponseDetailDto.builder()
                .brandCode(brand.getBrandCode())
                .englishName(brand.getEnglishName())
                .koreanName(brand.getKoreanName())
                .englishInitial(brand.getEnglishInitial())
                .koreanInitial(brand.getKoreanInitial())
                .build();
    }

    public GetBrandDetailResponseVo toVo() {
        return GetBrandDetailResponseVo.builder()
                .brandCode(brandCode)
                .englishName(englishName)
                .koreanName(koreanName)
                .englishInitial(englishInitial)
                .koreanInitial(koreanInitial)
                .build();
    }

}
