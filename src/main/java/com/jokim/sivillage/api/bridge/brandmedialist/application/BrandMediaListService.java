package com.jokim.sivillage.api.bridge.brandmedialist.application;

import com.jokim.sivillage.api.bridge.brandmedialist.dto.BrandMediaListRequestDto;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.BrandMediaListResponseDto;
import java.util.List;

public interface BrandMediaListService {

    void addBrandMediaList(BrandMediaListRequestDto brandMediaListRequestDto);

    List<BrandMediaListResponseDto> getBrandMediaList(String brandCode);

}
