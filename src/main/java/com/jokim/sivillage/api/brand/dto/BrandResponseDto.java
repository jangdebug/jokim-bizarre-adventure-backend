package com.jokim.sivillage.api.brand.dto;

import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.brand.vo.out.BrandDetailResponseVo;
import com.jokim.sivillage.api.brand.vo.out.BrandSummaryResponseVo;
import lombok.Builder;

@Builder
public class BrandResponseDto {

    private String brandCode;
    private String englishName;
    private String koreanName;
    private String englishInitial;
    private String koreanInitial;
    private String mediaCode;

    public static BrandResponseDto toDto(Brand brand, String mediaCode) {   // get summary
        return BrandResponseDto.builder()
                .englishName(brand.getEnglishName())
                .mediaCode(mediaCode)
                .build();
    }

    public static BrandResponseDto toDto(Brand brand) {   // get all brands
        return BrandResponseDto.builder()
                .brandCode(brand.getBrandCode())
                .englishName(brand.getEnglishName())
                .koreanName(brand.getKoreanName())
                .englishInitial(brand.getEnglishInitial())
                .koreanInitial(brand.getKoreanInitial())
                .build();
    }

    public BrandSummaryResponseVo toSummaryVo() {   // get summary
        return BrandSummaryResponseVo.builder()
                .englishName(englishName)
                .mediaCode(mediaCode)
                .build();
    }

    public BrandDetailResponseVo toDetailVo() {   // get all brands
        return BrandDetailResponseVo.builder()
                .brandCode(brandCode)
                .englishName(englishName)
                .koreanName(koreanName)
                .englishInitial(englishInitial)
                .koreanInitial(koreanInitial)
                .build();
    }

}
