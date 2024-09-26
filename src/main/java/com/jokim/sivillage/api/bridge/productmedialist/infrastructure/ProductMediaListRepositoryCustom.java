package com.jokim.sivillage.api.bridge.productmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.productmedialist.dto.out.AllProductMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.ThumbnailProductMediaListResponseDto;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMediaListRepositoryCustom {

    List<AllProductMediaListsResponseDto> getAllProductMediaLists(String productCode);

    Optional<ThumbnailProductMediaListResponseDto> getThumbnailByProductCode(String productCode);

}
