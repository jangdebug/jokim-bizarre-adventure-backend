package com.jokim.sivillage.product.presentation;

import com.jokim.sivillage.product.application.ProductService;
import com.jokim.sivillage.product.dto.out.FilteredProductDto;
import com.jokim.sivillage.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.product.vo.out.DailyHotProductResponseVo;
import com.jokim.sivillage.product.vo.out.ProductResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Filter;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    // 상품 데이터 보기
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponseVo> getProductData(@PathVariable Long productId){
        log.info("productId : {}", productId);
        ProductResponseDto productResponseDto = productService.getProductById(productId);
        log.info("productResponseDto : {}", productResponseDto.toString());
        return ResponseEntity.ok(productResponseDto.toResponseVo());

    }

    // 옵션 별  필터링 된 상품 보기
    @GetMapping("products/options")
    public ResponseEntity<List<ProductResponseDto>> getFilteredProductData(
            @RequestParam(value = "size-id") Long sizeId,
            @RequestParam(value = "color-id") Long colorId,
            @RequestParam(value = "etc-id") Long etcId) {
        log.info("productSize : {}", sizeId);
        log.info("productColor : {}", colorId);
        log.info("productEtc : {}", etcId);

        return ResponseEntity.ok(productService.getFilteredProducts(sizeId, colorId, etcId));
    }


    // 랜덤 상품 리스트 보기
    @GetMapping( "/main/random-product/")
    public ResponseEntity<List<ProductResponseDto>> getRandomProductData(@RequestParam(name = "count", required = false) Integer count){
        if (count == null){
            count = 5;
        }
        log.info("count : {}", count);

        return ResponseEntity.ok(productService.getRandomProducts(count));

    }






}
