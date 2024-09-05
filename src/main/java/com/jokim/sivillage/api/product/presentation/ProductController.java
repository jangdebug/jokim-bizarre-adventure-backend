package com.jokim.sivillage.api.product.presentation;

import com.jokim.sivillage.api.product.application.ProductService;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.vo.out.ProductResponseVo;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    // 상품 데이터 보기
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponseVo> getProduct(@PathVariable Long productId) {
        log.info("productId : {}", productId);
        ProductResponseDto productResponseDto = productService.getProductById(productId);
        log.info("productResponseDto : {}", productResponseDto.toString());
        return ResponseEntity.ok(productResponseDto.toResponseVo());
    }

    // 상품 데이터 입력
//    @PostMapping("/products")
//    public ResponseEntity<ProductResponseVo> getProduct(@RequestBody Product product) {
//        log.info("product : {}", product.toString());
//        ProductResponseDto productResponseDto = productService.getProductById(productId);
//
//        return ResponseEntity.ok(productResponseDto.toResponseVo());
//    }


    // 옵션 별  필터링 된 상품 보기
    @GetMapping("products/options")
    public ResponseEntity<List<ProductResponseVo>> getFilteredProduct(
        @RequestParam(value = "size-id") Long sizeId,
        @RequestParam(value = "color-id") Long colorId,
        @RequestParam(value = "etc-id") Long etcId) {
        log.info("productSize : {}", sizeId);
        log.info("productColor : {}", colorId);
        log.info("productEtc : {}", etcId);
        List<ProductResponseDto> productResponseDto = productService.getFilteredProducts(sizeId,
            colorId, etcId);

        ModelMapper modelMapper = new ModelMapper();
        List<ProductResponseVo> productResponseVo = productResponseDto.stream()
            .map(ResponseDto -> modelMapper.map(ResponseDto, ProductResponseVo.class)).toList();

        return ResponseEntity.ok(productResponseVo);
    }


    // 랜덤 상품 리스트 보기
    @GetMapping("/main/random-product/")
    public ResponseEntity<List<ProductResponseVo>> getRandomProduct(
        @RequestParam(name = "count", required = false) Integer count) {
        if (count == null) {
            count = 5;
        }
        log.info("count : {}", count);

        List<ProductResponseDto> productResponseDto = productService.getRandomProducts(count);
        ModelMapper modelMapper = new ModelMapper();
        List<ProductResponseVo> productResponseVo = productResponseDto.stream()
            .map(ResponseDto -> modelMapper.map(ResponseDto, ProductResponseVo.class)).toList();
        return ResponseEntity.ok(productResponseVo);
    }

    // 정렬된 상품 보기
    @GetMapping("/products/sort?sort-type={sortType}")
    public ResponseEntity<List<ProductResponseVo>> getProductBySortType(
        @RequestParam(name = "sort-type", required = false, defaultValue = "인기상품") String sortType) {
        log.info("sortType : {}", sortType);
        if (sortType.equals("인기상품")) {
            productService.getProductsBySortType(sortType);
        }

        return ResponseEntity.ok(Collections.emptyList());
    }


}
