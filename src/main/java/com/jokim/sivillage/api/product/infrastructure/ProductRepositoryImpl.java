package com.jokim.sivillage.api.product.infrastructure;

import static com.jokim.sivillage.api.bridge.productmedialist.domain.QProductMediaList.productMediaList;
import static com.jokim.sivillage.api.media.domain.QMedia.media;
import static com.jokim.sivillage.api.product.domain.QProduct.product;

import com.jokim.sivillage.api.media.domain.MediaType;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.domain.QProduct;
import com.jokim.sivillage.api.product.domain.QProductOption;
import com.jokim.sivillage.api.product.dto.out.ProductImageResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductListResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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

    private final JPAQueryFactory jpaQueryFactory;
    private final QProductOption productOption = QProductOption.productOption;

    @Override
    public List<Product> findFilteredProduct(Long sizeId, Long colorId, Long etcId) {

        QProduct product = QProduct.product;
        QProductOption productOption = QProductOption.productOption;

        log.info("productOption.size.id {}", productOption.size.id);

        return jpaQueryFactory.selectFrom(product)
            .join(productOption).on(product.productCode.eq(productOption.productCode))
            .where(eqSize(sizeId)
                , eqColor(colorId)
                , eqEtc(etcId))
            .fetch();
    }

    public BooleanExpression eqSize(Long sizeId) {
        if (sizeId == null) {
            return null;
        }
        return productOption.size.id.eq(sizeId);
    }

    public BooleanExpression eqColor(Long colorId) {
        if (colorId == null) {
            return null;
        }
        return productOption.color.id.eq(colorId);
    }

    public BooleanExpression eqEtc(Long etcId) {
        if (etcId == null) {
            return null;
        }
        return productOption.etc.id.eq(etcId);
    }

    @Override
    public ProductResponseDto findProductDtoByProductCode(String productCode) {

//        List<ProductResponseDto> productResponseDtos = jpaQueryFactory
//            .select(Projections.bean(
//                ProductResponseDto.class,
//                product.productCode.as("productCode"),
//                media.url.as("imageUrl"),
//                brand.mainName.as("brandName"),
//                Expressions.numberTemplate(Integer.class, "((1 - ({0}/{1}))*100) ",
//                    product.discountPrice,
//                    product.standardPrice).as("discountRate"),
//                product.productName.as("productName"),
//                product.isOnSale.as("isOnSale"),
//                product.standardPrice.as("price"),
//                product.discountPrice.as("amount"),
//                product.detail.as("detail"),
//                Expressions.numberTemplate(Double.class, "ROUND({0},1)", review.starPoint.avg())
//                    .as("starPoint"),
//                Expressions.asNumber(review.count()).as("reviewCount")
//
//            ))
//            .from(product)
//            .leftJoin(productMediaList).on(product.productCode.eq(
//                productMediaList.productCode))  // productMediaList와 product 조인
//            .leftJoin(media)
//            .on(productMediaList.mediaCode.eq(media.mediaCode))  // productMediaList와 media 조인
////            .leftJoin(brandProductList).on(product.productCode.eq(brandProductList.productCode))
////            .leftJoin(brand).on(brandProductList.brandCode.eq(brand.brandCode))
////            .leftJoin(review).on(product.productCode.eq(review.productCode))
//            // n * n * n * n ..
//            .where(product.productCode.eq(productCode))
//            .groupBy(
//                product.productCode,
//                media.url,
//                brand.mainName,
//                product.discountPrice,
//                product.standardPrice,
//                product.productName,
//                product.isOnSale,
//                product.standardPrice,
//                product.discountPrice,
//                product.detail
//            )
//            .fetch();

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
                        ).as("discountRate"),  // 드물겠지만 standardPrice가
                    product.productName.as("productName"),
//                product.isOnSale.as("isOnSale"),
                    product.standardPrice.as("price"),
                    product.discountPrice.as("amount"),
                    product.detail.as("detail")
                ))
            .from(product)
            .where(product.productCode.eq(productCode))
            .fetch();

        log.info("productResponseDtoList in repoImpl {}", productResponseDtoList);
        if (productResponseDtoList.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT);
        }

        ProductResponseDto productResponseDto = productResponseDtoList.get(0);
        log.info("productResponseDto in repoiImpl{}", productResponseDto);

//        // Hashtag 리스트 쿼리
//        List<HashtagResponseDto> hashtagResponseDtoList = jpaQueryFactory
//            .select(Projections.bean(
//                HashtagResponseDto.class,
//                hashtag.id.as("hashtagId"),
//                hashtag.value.as("value")
//            ))
//            .from(productHashtag)
//            .leftJoin(hashtag).on(productHashtag.hashtag.id.eq(hashtag.id))
//            .where(productHashtag.productCode.eq(productCode))
//            .fetch();

//        List<HashtagResponseVo> hashtagResponseVoList = hashtagResponseDtoList
//            .stream().map(h -> h.toVo()).toList();
//        hashtagResponseVoList.forEach(vo -> log.info("HashtagResponseVo: {}", vo));
//        log.info("hashtagResponseVos {}", hashtagResponseVoList);

        // DTO에 Hashtag 리스트 추가
//        if (productResponseDto != null) {
//            productResponseDto.setHashTag(hashtagResponseVos);
//        }
        return productResponseDto;
    }

    @Override
    public List<ProductListResponseDto> getRandomProducts(Integer count) {
        // 피드백 후 진행
        return List.of();
    }

    @Override
    public List<ProductImageResponseDto> getProductImagesByProductCode(String productCode) {
        List<ProductImageResponseDto> productResponseDtoList = jpaQueryFactory
            .select(
                Projections.fields(ProductImageResponseDto.class,
                    media.url.as("imageUrl")
                ))
            .from(media)
            .leftJoin(productMediaList).on(media.mediaCode.eq(productMediaList.mediaCode))
            .where(productMediaList.productCode.eq(productCode),
                media.type.eq(MediaType.valueOf("IMAGE"))
            )  // image 들만 반환
            .orderBy(media.id.desc())   // 순서 일정하게 보장
            .fetch();

        log.info("productResponseDtoList in ProductRepoImpl {}", productResponseDtoList.toString());

        return productResponseDtoList;
    }
}
