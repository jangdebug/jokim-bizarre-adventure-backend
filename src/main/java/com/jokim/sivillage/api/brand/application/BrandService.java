package com.jokim.sivillage.api.brand.application;

import com.jokim.sivillage.api.brand.dto.BrandRequestDto;
import com.jokim.sivillage.api.brand.dto.BrandResponseDto;

import java.util.List;

public interface BrandService {

    void addBrand(BrandRequestDto brandRequestDto);

    BrandResponseDto getBrandSummary(String brandCode);
    List<BrandResponseDto> getAllBrands();

}
