package com.jokim.sivillage.api.product.application;


import com.jokim.sivillage.api.brand.infrastructure.BrandRepository;
import com.jokim.sivillage.api.bridge.brandproductlist.repository.BrandProductListRepository;
import com.jokim.sivillage.api.hashtag.infrastructure.ProductHashtagRepository;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
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

    // 옵션 별 상품 반환
//    @Override
//    @Transactional
//    public List<ProductResponseDto> getFilteredProducts(Long sizeId, Long colorId, Long etcId) {
//        log.info("before productRepository");
//        List<Product> products = productRepositoryCustom.findFilteredProduct(sizeId, colorId,
//            etcId);
//        log.info("List<Product> products[0] {}", products.get(0).toString());
////        List<ProductResponseDto> productResponseDtos = products.stream().map(
////            ProductResponseDto
////        )
//        ModelMapper modelMapper = new ModelMapper();
//        List<ProductResponseDto> productResponseDtos = products.stream()
//            .map(product -> modelMapper.map(product, ProductResponseDto.class))
//            .collect(Collectors.toList());
//
//        log.info("productResponseDtos {}", productResponseDtos);
//
//        return productResponseDtos;
//    }

    @Override
    public List<ProductListResponseDto> getRandomProducts(Integer count) {

        return productRepositoryCustom.getRandomProducts(count);

    }

    // category별 product => 작성 중 정지
//    @Override
//    public List<ProductListResponseVo> getProductsByCategory(Long categoryId) {
//        // categoryId 에 해당하는 productCategoryList entity 반환
//        List<ProductCategoryList> productCategoryLists = productCategoryListRepository.findById(
//            categoryId);
//        if (productCategoryLists.isEmpty()) {
//            return new ArrayList<>();
//        }
//        // categoryId 에 해당하는 productCodes 반환하기
//        List<String> productCodes = productCategoryLists.stream()
//            .map(ProductCategoryList::getProductCode).toList();
//
//        // ProductCode에 해당하는 product 가져오기
//        List<Product> products = productCodes.stream().
//            map(productCode -> productRepository.findByProductCode(productCode).get()).toList();
//
//        // ProductListResponseVo로 만들기
//        List<ProductListResponseVo> productListResponseVos = products.stream().map(
//            product ->
//                ProductListResponseVo.builder()
//                    .productCode(product.getProductCode())
//                    .productName(product.getProductName())
//                    .imageUrl(
//                        "https://image.sivillage.com/upload/C00001/goods/org/293/220802002890293.jpg?RS=750&SP=1")
//                    // productMediaListRepository.findByProductCode().getMediaCode
//                    //todo 중개테이블 생성 후 확인
//                    // temp 값 입력
//                    .discountRate()
//                    .brandName()
//                    .isWish(false) // 초기값
//                    .build()
//
//        );
//
//        return List.of();
//    }

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