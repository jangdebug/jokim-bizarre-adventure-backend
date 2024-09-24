package com.jokim.sivillage.api.bridge.brandmedialist.infrastructure;

import com.jokim.sivillage.api.brand.dto.out.BrandSummaryResponseDto;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.out.AllBrandMediaListsResponseDto;
import java.util.List;

public interface BrandMediaListRepositoryCustom {

    List<AllBrandMediaListsResponseDto> getAllBrandMediaLists(String brandCode);

    String getBrandLogoUrl(String brandCode);

}
