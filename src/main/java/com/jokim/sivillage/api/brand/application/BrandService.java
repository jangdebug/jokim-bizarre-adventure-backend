package com.jokim.sivillage.api.brand.application;

import com.jokim.sivillage.api.brand.dto.in.BrandRequestDto;
import com.jokim.sivillage.api.brand.dto.out.BrandResponseDetailDto;

import com.jokim.sivillage.api.brand.dto.out.BrandSummaryResponseDto;
import java.util.List;

public interface BrandService {

    void addBrand(BrandRequestDto brandRequestDto);

    List<BrandResponseDetailDto> getAllBrands();
    BrandSummaryResponseDto getBrandSummary(String brandCode, Boolean isLogoRequired);

    void updateBrand(BrandRequestDto brandRequestDto);

    void deleteBrand(String brandCode);
}
