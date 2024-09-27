package com.jokim.sivillage.api.product.infrastructure;


import static com.jokim.sivillage.api.product.domain.QProduct.product;
import static com.jokim.sivillage.api.product.domain.QProductOption.productOption;
import static com.jokim.sivillage.api.product.domain.option.QColor.color;
import static com.jokim.sivillage.api.product.domain.option.QEtc.etc;
import static com.jokim.sivillage.api.product.domain.option.QSize.size;

import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.dto.out.option.ProductOptionResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private static final int DEFAULT_PAGE_SIZE = 40;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public ProductResponseDto findProductDtoByProductCode(String productCode) {

        List<ProductResponseDto> productResponseDtoList = jpaQueryFactory
            .select(
                Projections.fields(ProductResponseDto.class,
                    product.productCode.as("productCode"),
                    Expressions.cases()
                        .when(product.standardPrice.eq(0.0))
                        .then(-1)  // standardPrice가 0이면 0을 반환
                        .otherwise(
                            Expressions.numberTemplate(Integer.class, "((1 - ({0}/{1}))*100)",
                                product.discountPrice,
                                product.standardPrice)
                        ).as("discountRate"),
                    product.productName.as("productName"),
                    product.standardPrice.as("price"),
                    product.discountPrice.as("amount"),
                    product.detail.as("detail"),
                    product.brandCode.as("brandCode")
                ))
            .from(product)
            .where(product.productCode.eq(productCode))
            .fetch();

        log.info("productResponseDtoList in repoImpl {}", productResponseDtoList);
        ProductResponseDto productResponseDto = null;
        if (!productResponseDtoList.isEmpty()) {
            productResponseDto = productResponseDtoList.get(0);
        }

        return productResponseDto;
    }


    @Override
    public ProductListResponseDto getProductListByProductCode(String productCode) {

        ProductListResponseDto productListResponseDto = jpaQueryFactory
            .select(
                Projections.fields(
                    ProductListResponseDto.class,
                    product.productCode.as("productCode"),
                    product.productName.as("productName"),
                    product.discountPrice.as("price"),
                    Expressions.cases()
                        .when(product.standardPrice.eq(0.0))
                        .then(-1)  // standardPrice가 0이면 0을 반환
                        .otherwise(
                            Expressions.numberTemplate(Integer.class, "((1 - ({0}/{1}))*100)",
                                product.discountPrice,
                                product.standardPrice)
                        ).as("discountRate"),
                    product.brandCode.as("brandCode")
                ))
            .from(product)
            .where(product.productCode.eq(productCode))
            .fetchOne();

        return productListResponseDto;
    }


    @Override
    public List<ProductListResponseDto> getMostDiscountProduct(Integer count) {

        List<ProductListResponseDto> productListResponseDtoList = jpaQueryFactory.select(
                Projections.fields(
                    ProductListResponseDto.class,
                    product.productCode.as("productCode"),
                    product.productName.as("productName"),
                    product.discountPrice.as("price"),
                    Expressions.cases()
                        .when(product.standardPrice.eq(0.0))
                        .then(-1)  // standardPrice가 0이면 0을 반환
                        .otherwise(
                            Expressions.numberTemplate(Integer.class, "((1 - ({0}/{1}))*100)",
                                product.discountPrice,
                                product.standardPrice)
                        ).as("discountRate"),
                    product.brandCode.as("brandCode")
                ))
            .from(product)
            .orderBy(Expressions.numberTemplate(Integer.class, "((1 - ({0}/{1}))*100)",
                product.discountPrice,
                product.standardPrice).desc())
            .limit(count)
            .fetch();

        return productListResponseDtoList;
    }

    @Override
    public ProductOptionResponseDto getProductOptionListByProductCode(String productCode) {
        // Size 목록 조회
        List<String> sizes = jpaQueryFactory
            .selectDistinct(size.value)  // productOption.size.value로 distinct 값을 선택
            .from(productOption)
            .leftJoin(size).on(productOption.size.id.eq(size.id))  // productOption의 size와 size를 조인
            .where(productOption.productCode.eq(productCode))  // productCode 조건
            .fetch();

        List<String> colors = jpaQueryFactory
            .selectDistinct(color.value)
            .from(productOption)
            .leftJoin(color).on(productOption.color.id.eq(color.id))
            .where(productOption.productCode.eq(productCode))
            .fetch();

        List<String> etcs = jpaQueryFactory
            .selectDistinct(etc.value)
            .from(productOption)
            .leftJoin(etc).on(productOption.etc.id.eq(etc.id))
            .where(productOption.productCode.eq(productCode))
            .fetch();



        return ProductOptionResponseDto.builder()
            .sizeList(sizes)
            .colorList(colors)
            .etcList(etcs)
            .build();

    }

}
