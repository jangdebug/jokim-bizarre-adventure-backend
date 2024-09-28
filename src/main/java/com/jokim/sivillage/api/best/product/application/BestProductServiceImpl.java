package com.jokim.sivillage.api.best.product.application;

import com.jokim.sivillage.api.best.product.domain.BestProduct;
import com.jokim.sivillage.api.best.product.dto.BestProductResponseDto;
import com.jokim.sivillage.api.best.product.infrastructure.BestProductRepository;
import com.jokim.sivillage.api.best.product.vo.BestProductResponseVo;
import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.brand.infrastructure.BrandRepository;
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
public class BestProductServiceImpl implements BestProductService{

    private final BestProductRepository bestProductRepository;
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    @Override
    public List<BestProductResponseDto> getBestProduct() {
        List<BestProduct> bestProducts = bestProductRepository.findAll();
        // rankValue 기준으로 오름차순 정렬
        return bestProducts.stream()
            .sorted(Comparator.comparing(BestProduct::getRankValue)) // rankValue 기준 오름차순 정렬
            .map(bestProduct -> {
                Product product =productRepository.findByProductCode(bestProduct.getProductCode()).
                    orElseThrow(()->new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));

                Integer discountRate = (int) Math.round(((product.getStandardPrice() - product.getDiscountPrice()) / (double) product.getStandardPrice())*100);

                Brand brand = brandRepository.findByBrandCode(product.getBrandCode()).orElseThrow(()
                    -> new BaseException(BaseResponseStatus.NOT_EXIST_BRAND));
                return BestProductResponseDto.toDto(bestProduct, product, brand, discountRate);
            }) // DTO로 변환
            .toList();
    }
}
