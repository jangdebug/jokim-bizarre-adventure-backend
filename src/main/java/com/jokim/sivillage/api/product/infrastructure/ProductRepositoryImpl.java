package com.jokim.sivillage.api.product.infrastructure;


import static com.jokim.sivillage.api.product.domain.QProduct.product;
import static com.jokim.sivillage.api.product.domain.QProductOption.productOption;

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

        return jpaQueryFactory
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
    }


    @Override
    public List<ProductListResponseDto> getMostDiscountProduct(Integer count) {

        return jpaQueryFactory.select(
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
    }

    @Override
    public List<ProductOptionResponseDto> getProductOptionListByProductCode(String productCode) {

        return jpaQueryFactory.select(
            Projections.fields(
                ProductOptionResponseDto.class,
                productOption.productOptionCode,
                productOption.size.value.as("sizeValue"),
                productOption.color.value.as("colorValue"),
                productOption.stock.as("stock")
            ))
            .from(productOption)
            .where(productOption.productCode.eq(productCode))
            .fetch();

    }

}
