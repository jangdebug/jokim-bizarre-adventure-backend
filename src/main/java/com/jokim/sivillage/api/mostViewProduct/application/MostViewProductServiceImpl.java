package com.jokim.sivillage.api.mostViewProduct.application;

import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.brand.infrastructure.BrandRepository;
import com.jokim.sivillage.api.mostViewProduct.domain.MostViewProduct;
import com.jokim.sivillage.api.mostViewProduct.dto.MostViewProductDto;
import com.jokim.sivillage.api.mostViewProduct.infrastructure.MostViewProductRepository;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.infrastructure.ProductRepository;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class MostViewProductServiceImpl implements MostViewProductService {

    private final MostViewProductRepository mostViewProductRepository;
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    @Override
    public List<MostViewProductDto> getMostViewProduct() {
        List<MostViewProduct> mostViewProducts = mostViewProductRepository.findAll();
        // rankValue 기준으로 오름차순 정렬
        return mostViewProducts.stream()
            .sorted(Comparator.comparing(MostViewProduct::getRankValue)) // rankValue 기준 오름차순 정렬
            .map(mostViewProduct -> {
                Product product =productRepository.findByProductCode(mostViewProduct.getProductCode()).
                    orElseThrow(()->new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));

                Integer discountRate = (int) Math.round(((product.getStandardPrice() - product.getDiscountPrice()) / (double) product.getStandardPrice())*100);

                Brand brand = brandRepository.findByBrandCode(product.getBrandCode()).orElseThrow(()
                    -> new BaseException(BaseResponseStatus.NOT_EXIST_BRAND));
                return MostViewProductDto.toDto(mostViewProduct, product, brand, discountRate);
            }) // DTO로 변환
            .toList();
    }
}