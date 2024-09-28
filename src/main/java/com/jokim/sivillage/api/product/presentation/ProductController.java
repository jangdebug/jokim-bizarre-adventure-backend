package com.jokim.sivillage.api.product.presentation;

import com.jokim.sivillage.api.product.application.ProductService;
import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.dto.out.option.ProductOptionResponseDto;
import com.jokim.sivillage.api.product.vo.in.ProductRequestVo;
import com.jokim.sivillage.api.product.vo.out.ProductListResponseVo;
import com.jokim.sivillage.api.product.vo.out.ProductOptionResponseVo;
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
        ProductResponseDto productResponseDto = productService.getProductByProductCode(productCode);
        ProductResponseVo response = Optional.ofNullable(productResponseDto)
            .map(ProductResponseDto::toResponseVo).orElse(null);
        return new BaseResponse<>(response);
    }

    // 상품 데이터 입력
    @PostMapping("/products")
    @Operation(summary = "상품 데이터 저장", description = "상품 데이터를 저장한다.")
    public BaseResponse<Void> saveProduct(@RequestBody ProductRequestVo productRequestVo) {
        productService.saveProduct(ProductRequestDto.toDto(productRequestVo));
        return new BaseResponse<>();
    }

    // 상품 업데이트 하기
    @Operation(summary = "상품 데이터 업데이트", description = "상품코드로 상품 데이터를 수정한다.")
    @PutMapping("/products")
    public BaseResponse<Void> updateProduct(@RequestBody ProductRequestVo productRequestVo) {
        productService.updateProduct(ProductRequestDto.toDto(productRequestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 데이터 삭제", description = "상품코드로 상품 데이터를 삭제한다.")
    @DeleteMapping("/products/{productCode}")
    public BaseResponse<Void> deleteProduct(@PathVariable String productCode) {
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
        // 잘못 찾았을 시
        if (productListResponseDto == null) {
            return new BaseResponse<>();
        }

        return new BaseResponse<>(productListResponseDto.toResponseVo());
    }


    // 랜덤 상품 리스트 보기
    @Operation(summary = "랜덤 상품 리스트 보기", description = "주어진 갯수만큼 상품 리스트를 반환한다.")
    @GetMapping("/main/random-product")
    public BaseResponse<List<ProductListResponseVo>> getRandomProduct(
        @RequestParam(name = "count", required = false) Integer count) {
        List<ProductListResponseDto> productListResponseDtos = productService.getRandomProducts(
            count == null ? 5 : count);
        List<ProductListResponseVo> productListResponseVos = productListResponseDtos.stream()
            .map(ProductListResponseDto::toResponseVo).toList();
        return new BaseResponse<>(productListResponseVos);
    }

    // 최고 할인 상품 보기
    @Operation(summary = "최고 할인 상품 리스트 보기", description = "최고 할인 상품 리스트를 반환한다.")
    @GetMapping("/main/most-discount-rate/{count}")
    public BaseResponse<List<ProductListResponseVo>> getMostDiscountProduct(
        @RequestParam(name = "count", required = false) Integer count) {
        List<ProductListResponseDto> productListResponseDtos = productService.getMostDiscountProduct(
            count == null ? 5 : count);
        List<ProductListResponseVo> productListResponseVos = productListResponseDtos.stream()
            .map(ProductListResponseDto::toResponseVo).toList();
        return new BaseResponse<>(productListResponseVos);
    }

    // 상품 옵션 보기
    @Operation(summary = "상품 옵션 리스트 보기", description = "상품에 해당하는 옵션을 반환한다.")
    @GetMapping("/products-options/{productCode}")
    public BaseResponse<List<ProductOptionResponseVo>> getProductsOptions(@PathVariable String productCode) {

        return new BaseResponse<>(productService.getProductOptionByProductCode(productCode).stream().map(
            ProductOptionResponseDto::toResponseVo).toList());

    }



}
