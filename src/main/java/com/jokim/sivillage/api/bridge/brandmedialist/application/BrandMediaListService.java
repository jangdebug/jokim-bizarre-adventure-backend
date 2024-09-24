package com.jokim.sivillage.api.bridge.brandmedialist.application;

import com.jokim.sivillage.api.bridge.brandmedialist.dto.in.BrandMediaListRequestDto;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.out.AllBrandMediaListsResponseDto;
import java.util.List;

public interface BrandMediaListService {

    void addBrandMediaList(BrandMediaListRequestDto brandMediaListRequestDto);

    List<AllBrandMediaListsResponseDto> getAllBrandMediaLists(String brandCode);

    void updateBrandMediaList(BrandMediaListRequestDto brandMediaListRequestDto);

}
