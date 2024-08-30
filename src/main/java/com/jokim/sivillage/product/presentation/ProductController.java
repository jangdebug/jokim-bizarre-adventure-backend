package com.jokim.sivillage.product.presentation;

import com.jokim.sivillage.product.application.ProductService;
import com.jokim.sivillage.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.product.vo.out.DailyHotProductResponseVo;
import com.jokim.sivillage.product.vo.out.ProductResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(productResponseDto.toResponseVo());   //TODO
    }





}
