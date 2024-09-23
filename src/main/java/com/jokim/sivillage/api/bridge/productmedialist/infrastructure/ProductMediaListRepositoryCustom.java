package com.jokim.sivillage.api.bridge.productmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.productmedialist.dto.out.ProductMediaListResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMediaListRepositoryCustom {

    List<ProductMediaListResponseDto> getProductMediaList(String productCode);

}
