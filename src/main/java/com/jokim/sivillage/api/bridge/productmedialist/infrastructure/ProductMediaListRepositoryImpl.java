package com.jokim.sivillage.api.bridge.productmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.productmedialist.domain.QProductMediaList;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.AllProductMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.QAllProductMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.QThumbnailProductMediaListResponseDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.ThumbnailProductMediaListResponseDto;
import com.jokim.sivillage.api.media.domain.QMedia;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductMediaListRepositoryImpl implements ProductMediaListRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QProductMediaList productMediaList = QProductMediaList.productMediaList;
    private final QMedia media = QMedia.media;

    @Override
    public List<AllProductMediaListsResponseDto> getAllProductMediaLists(String productCode) {

        return jpaQueryFactory.select(new QAllProductMediaListsResponseDto(
                media.mediaCode,
                media.url,
                media.mediaType.stringValue(),
                productMediaList.isThumbnail))
            .from(productMediaList)
            .rightJoin(media).on(productMediaList.mediaCode.eq(media.mediaCode))
            .where(productMediaList.productCode.eq(productCode))
            .orderBy(media.id.asc())
            .fetch();
    }

    @Override
    public Optional<ThumbnailProductMediaListResponseDto> getThumbnailByProductCode(String productCode) {

        BooleanExpression condition = Expressions.allOf(
            productMediaList.productCode.eq(productCode),
            productMediaList.isThumbnail.eq(true));

        return Optional.ofNullable(jpaQueryFactory.select(new QThumbnailProductMediaListResponseDto(
                media.mediaCode,
                media.url,
                media.mediaType.stringValue()))
            .from(productMediaList)
            .rightJoin(media).on(productMediaList.mediaCode.eq(media.mediaCode))
            .where(condition)
            .fetchOne());

    }

}
