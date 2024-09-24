package com.jokim.sivillage.api.brand.dto.in;

import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.brand.vo.in.AddBrandRequestVo;
import com.jokim.sivillage.api.brand.vo.in.UpdateBrandRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandRequestDto {

    private String brandCode;
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

    public static BrandRequestDto toDto(UpdateBrandRequestVo updateBrandRequestVo) {
        return BrandRequestDto.builder()
                .brandCode(updateBrandRequestVo.getBrandCode())
                .englishName(updateBrandRequestVo.getEnglishName())
                .koreanName(updateBrandRequestVo.getKoreanName())
                .englishInitial(updateBrandRequestVo.getEnglishInitial())
                .koreanInitial(updateBrandRequestVo.getKoreanInitial())
                .build();
    }

    public Brand toEntity(String brandCode) {   // add Brand
        return Brand.builder()
                .brandCode(brandCode)
                .englishName(englishName)
                .koreanName(koreanName)
                .englishInitial(englishInitial)
                .koreanInitial(koreanInitial)
                .build();
    }

    public Brand toEntity(Long id) {    // update Brand
        return Brand.builder()
                .id(id)
                .brandCode(brandCode)
                .englishName(englishName)
                .koreanName(koreanName)
                .englishInitial(englishInitial)
                .koreanInitial(koreanInitial)
                .build();
    }

}
