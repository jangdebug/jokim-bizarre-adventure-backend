package com.jokim.sivillage.api.brand.dto;

import com.jokim.sivillage.api.brand.domain.Brand;
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
                .brandCode(brand.getBrandCode())
                .englishName(brand.getEnglishName())
                .koreanName(brand.getKoreanName())
                .englishInitial(brand.getEnglishInitial())
                .koreanInitial(brand.getKoreanInitial())
                .mediaCode(mediaCode)
                .build();
    }

    public BrandSummaryResponseVo toVo() {      // get summary
        return BrandSummaryResponseVo.builder()
                .englishName(englishName)
                .mediaCode(mediaCode)
                .build();
    }

}
