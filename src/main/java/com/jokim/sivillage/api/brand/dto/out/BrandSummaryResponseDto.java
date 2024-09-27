package com.jokim.sivillage.api.brand.dto.out;

import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.brand.vo.out.GetBrandSummaryResponseVo;
import lombok.Builder;

@Builder
public class BrandSummaryResponseDto {

    private String englishName;
    private String koreanName;
    private String mediaUrl;

    public static BrandSummaryResponseDto toDto(Brand brand, String mediaUrl) {
        return BrandSummaryResponseDto.builder()
            .englishName(brand.getEnglishName())
            .koreanName(brand.getKoreanName())
            .mediaUrl(mediaUrl)
            .build();
    }

    public GetBrandSummaryResponseVo toVo() {
        return GetBrandSummaryResponseVo.builder()
            .englishName(englishName)
            .koreanName(koreanName)
            .mediaUrl(mediaUrl)
            .build();
    }

}
