package com.jokim.sivillage.api.product.application;


import com.jokim.sivillage.api.brand.infrastructure.BrandRepository;
import com.jokim.sivillage.api.bridge.brandproductlist.repository.BrandProductListRepository;
import com.jokim.sivillage.api.hashtag.infrastructure.ProductHashtagRepository;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.ProductImageResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
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
    private final BrandProductListRepository brandProductListRepository;


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
    public List<ProductImageResponseDto> getProductImageByProductCode(String productCode) {

        return productRepositoryCustom.getProductImagesByProductCode(productCode);
    }


    @Override
    public List<ProductListResponseDto> getRandomProducts(Integer count) {

        return productRepositoryCustom.getRandomProducts(count);

    }

    // category별 product => 작성 중 정지
    @Override
    public List<ProductListResponseDto> getProductListByProductCodeList(
        List<String> productCodeList) {

        return productRepositoryCustom.getProductListByProductCodeList(productCodeList);

    }

    @Override
    public List<ProductListResponseDto> getProductListByOptions(Long sizeId, Long colorId,
        Long etcId) {

        return productRepositoryCustom.getProductListByOpions(sizeId, colorId, etcId);
    }

    //    @Override
//    public List<DailyHotProductResponseDto> getDailyHotProducts() {
//
//        return List.of();
//    }

//    @Override
//    public List<ProductResponseDto> getRandomProducts(Integer count) {
//        List<Product> products = productRepository.findRandomProducts(count);
//        ModelMapper modelMapper = new ModelMapper();
//        List<ProductResponseDto> productResponseDtos = products.stream()
//            .map(product -> modelMapper.map(product, ProductResponseDto.class))
//            .collect(Collectors.toList());
//        return productResponseDtos;
//    }
//
//    @Override
//    public List<ProductResponseDto> getProductsBySortType(String sortType) {
//
//        return List.of();
//    }
}