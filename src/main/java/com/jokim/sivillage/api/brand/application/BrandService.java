package com.jokim.sivillage.api.brand.application;

import com.jokim.sivillage.api.brand.dto.BrandRequestDto;
import com.jokim.sivillage.api.brand.dto.BrandResponseDto;

public interface BrandService {

    void addBrand(BrandRequestDto brandRequestDto);

    BrandResponseDto getBrandSummary(String brandCode);

}
