package com.jokim.sivillage.api.product.application;


import com.jokim.sivillage.api.brand.infrastructure.BrandRepository;
import com.jokim.sivillage.api.hashtag.infrastructure.ProductHashtagRepository;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.dto.out.option.ProductOptionResponseDto;
import com.jokim.sivillage.api.product.infrastructure.ProductRepository;
import com.jokim.sivillage.api.product.infrastructure.ProductRepositoryCustom;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    //dto.onSearchChangeBrandName();

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final ProductHashtagRepository productHashtagRepository;
    private final ProductRepositoryCustom productRepositoryCustom;


    @Override
    public ProductResponseDto getProductByProductCode(String productCode) {

        ProductResponseDto productResponseDto = productRepositoryCustom.findProductDtoByProductCode(
            productCode);

        return productResponseDto;

    }

    @Transactional
    @Override
    public void saveProduct(ProductRequestDto productRequestDto) {

        String productCode = productRequestDto.getProductCode();
        if (productRepository.findByProductCode(productCode).isPresent()) {
            throw new BaseException(BaseResponseStatus.ALREADY_EXIST_PRODUCT_CODE);
        }

        productRepository.save(productRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void updateProduct(ProductRequestDto productRequestDto) {
        Product product = productRepository.findByProductCode(productRequestDto.getProductCode())
            .orElseThrow(
                () -> new IllegalArgumentException("해당 상품이 존재하지 않습니다.")
            );

        productRepository.save(productRequestDto.toEntity(product.getId()));
    }

    @Transactional
    @Override
    public void deleteProduct(String productCode) {
        Product product = productRepository.findByProductCode(productCode).orElseThrow(
            () -> new IllegalArgumentException("해당 상품이 존재하지 않습니다.")
        );
        productRepository.deleteById(product.getId());
    }




    @Override
    public List<ProductListResponseDto> getRandomProducts(Integer count) {
        List<Product> productList = productRepository.getRandomProducts(count);
        // 랜덤으로 productCode 5개 얻기
        List<String> productCodeList = productList.stream().map(product -> product.getProductCode())
            .toList();
        List<ProductListResponseDto> productListResponseDtoList = productCodeList.stream()
            .map(productcode -> productRepositoryCustom.getProductListByProductCode(productcode))
            .toList();
        return productListResponseDtoList;


    }

    @Override
    public ProductListResponseDto getProductListByProductCode(
        String productCode) {

        return productRepositoryCustom.getProductListByProductCode(productCode);
    }

    @Override
    public List<ProductListResponseDto> getMostDiscountProduct(Integer count) {
        return productRepositoryCustom.getMostDiscountProduct(count);
    }

    @Override
    public List<ProductOptionResponseDto> getProductOptionByProductCode(String productCode){
        List<ProductOptionResponseDto> productOptionResponseDtos = productRepositoryCustom.getProductOptionListByProductCode(productCode);
        log.info("productOptionResponseDtos : {} in serviceImpl", productOptionResponseDtos);
        return productOptionResponseDtos;
    }

}