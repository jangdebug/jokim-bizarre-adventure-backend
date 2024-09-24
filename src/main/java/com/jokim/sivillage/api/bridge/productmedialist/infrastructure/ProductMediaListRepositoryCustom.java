package com.jokim.sivillage.api.bridge.productmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.productmedialist.dto.out.AllProductMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.ThumbnailProductMediaListResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMediaListRepositoryCustom {

    List<AllProductMediaListsResponseDto> getAllProductMediaLists(String productCode);

    ThumbnailProductMediaListResponseDto getThumbnailByProductCode(String productCode);

}
