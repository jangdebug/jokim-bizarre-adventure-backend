package com.jokim.sivillage.api.product.presentation;

import com.jokim.sivillage.api.product.application.ProductService;
import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.vo.in.ProductRequestVo;
import com.jokim.sivillage.api.product.vo.out.ProductListResponseVo;
import com.jokim.sivillage.api.product.vo.out.ProductResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {

    private final ProductService productService;


    // 상품 데이터 보기
    @Operation(summary = "상품 데이터 보기(상품관련)", description = "상품코드로 상품 관련 데이터를 조회한다.")
    @GetMapping("/products/{productCode}")
    public BaseResponse<ProductResponseVo> getProduct(@PathVariable String productCode) {
        log.info("productCoded : {}", productCode);
        ProductResponseDto productResponseDto = productService.getProductByProductCode(productCode);
        ProductResponseVo response = Optional.ofNullable(productResponseDto)
            .map(ProductResponseDto::toResponseVo).orElse(null);
        return new BaseResponse<>(response);
    }

    // 상품 이미지 데이터 보기
    // media 부분에 구현되어 있어서 보류
//    @Operation(summary = "상품 데이터 보기(이미지관련)", description = "상품코드로 이미지 관련 데이터를 조회한다.")
//    @GetMapping("/product-image/{productCode}")
//    public BaseResponse<List<ProductImageResponseVo>> getProductImage(
//        @PathVariable(required = false) String productCode) {
//        if (productCode == null) {
//            return new BaseResponse<>();
//        }
//        List<ProductImageResponseDto> productImageResponseDtoList = productService.getProductImageByProductCode(
//            productCode);
//        log.info("productImageResponseDtoList in ProductController {}",
//            Optional.ofNullable(productImageResponseDtoList).orElse(Collections.emptyList()));
//
//        return new BaseResponse<>(Optional.ofNullable(productImageResponseDtoList)
//            .map(list -> list.stream().map(ProductImageResponseDto::toVo).toList()).orElse(null));
//    }

    // 상품 데이터 입력
    @PostMapping("/products")
    @Operation(summary = "상품 데이터 저장", description = "상품 데이터를 저장한다.")
    public BaseResponse<Void> saveProduct(@RequestBody ProductRequestVo productRequestVo) {
        log.info("productRequestVo : {} in saveProduct", productRequestVo.toString());
        ProductRequestDto productRequestDto = ProductRequestDto.toDto(productRequestVo);
        log.info("productRequestDto : in ProductController {}", productRequestDto.toString());
        productService.saveProduct(ProductRequestDto.toDto(productRequestVo));
        return new BaseResponse<>();
    }

    // 상품 업데이트 하기
    @Operation(summary = "상품 데이터 업데이트", description = "상품코드로 상품 데이터를 수정한다.")
    @PutMapping("/products")
    public BaseResponse<Void> updateProduct(@RequestBody ProductRequestVo productRequestVo) {
        log.info("productRequestVo : {}", productRequestVo.toString());
        ProductRequestDto productRequestDto = ProductRequestDto.toDto(productRequestVo);
        productService.updateProduct(productRequestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 데이터 삭제", description = "상품코드로 상품 데이터를 삭제한다.")
    @DeleteMapping("/products/{productCode}")
    public BaseResponse<Void> deleteProduct(@PathVariable String productCode) {
        log.info("productCode : {}", productCode);
        productService.deleteProduct(productCode);
        return new BaseResponse<>();

    }


    // 상품 리스트 반환
    @Operation(summary = "상품 리스트 정보 보기", description = "상품 코드로 상품 리스트 정보를 조회한다")
    @GetMapping("/products/product-code-list")
    public BaseResponse<ProductListResponseVo> getProductList(
        @RequestParam(required = false) String productCode) {

        // 상품 코드 잘못 왔을 시
        if (productCode == null) {
            return new BaseResponse<>();
        }

        ProductListResponseDto productListResponseDto = productService.getProductListByProductCode(
            productCode);

        if (productListResponseDto == null) {
            return new BaseResponse<>();
        }

        return new BaseResponse<>(productListResponseDto.toResponseVo());
    }

    // 옵션 별  필터링 된 상품 보기 => 기능 x product-category-list 쪽에서 기능 이동
//    @Operation(summary = "옵션 별 상품 리스트 보기", description = "옵션에 따른 상품 목록을 조회한다(상품에서 작동한다 태그용)")
//    @GetMapping("/products/options")
//    public BaseResponse<List<ProductListResponseVo>> getFilteredProduct(
//        @RequestParam(value = "size-id") Long sizeId,
//        @RequestParam(value = "color-id") Long colorId,
//        @RequestParam(value = "etc-id") Long etcId) {
//        log.info("productSize, color, etc id : {}, {}, {}", sizeId, colorId, etcId);
//        List<ProductListResponseDto> productListResponseDtoList = productService.getProductListByOptions(
//            sizeId, colorId, etcId);
//
//        return new BaseResponse<>(
//            productListResponseDtoList.stream().map(ProductListResponseDto::toResponseVo).toList());
//    }

    // 랜덤 상품 리스트 보기
    @Operation(summary = "랜덤 상품 리스트 보기", description = "주어진 갯수만큼 상품 리스트를 반환한다.")
    @GetMapping("/main/random-product")
    public BaseResponse<List<ProductListResponseVo>> getRandomProduct(
        @RequestParam(name = "count", required = false) Integer count) {
        if (count == null) {
            count = 5;
        }
        List<ProductListResponseDto> productListResponseDtos = productService.getRandomProducts(
            count);
        List<ProductListResponseVo> productListResponseVos = productListResponseDtos.stream()
            .map(ProductListResponseDto::toResponseVo).toList();
        return new BaseResponse<>(productListResponseVos);
    }

    // 최고 할인 상품 보기
    @Operation(summary = "최고 할인 상품 리스트 보기", description = "최고 할인 상품 리스트를 반환한다.")
    @GetMapping("/main/most-discount-rate/{count}")
    public BaseResponse<List<ProductListResponseVo>> getMostDiscountProduct(
        @RequestParam(name = "count", required = false) Integer count) {
        if (count == null) {
            count = 5;
        }
        List<ProductListResponseDto> productListResponseDtos = productService.getMostDiscountProduct(
            count);
        List<ProductListResponseVo> productListResponseVos = productListResponseDtos.stream()
            .map(ProductListResponseDto::toResponseVo).toList();
        return new BaseResponse<>(productListResponseVos);
    }

    // 정렬된 상품 보기
//    @GetMapping("/products/sort?sort-type={sortType}")
//    public ResponseEntity<List<ProductResponseVo>> getProductBySortType(
//        @RequestParam(name = "sort-type", required = false, defaultValue = "인기상품") String sortType) {
//        log.info("sortType : {}", sortType);
//        if (sortType.equals("인기상품")) {
//            productService.getProductsBySortType(sortType);
//        }
//
//        return ResponseEntity.ok(Collections.emptyList());
//    }


}
