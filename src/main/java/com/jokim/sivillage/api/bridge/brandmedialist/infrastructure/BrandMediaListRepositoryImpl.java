package com.jokim.sivillage.api.bridge.brandmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.brandmedialist.domain.QBrandMediaList;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.out.AllBrandMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.out.QAllBrandMediaListsResponseDto;
import com.jokim.sivillage.api.media.domain.QMedia;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BrandMediaListRepositoryImpl implements BrandMediaListRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QBrandMediaList brandMediaList = QBrandMediaList.brandMediaList;
    private final QMedia media = QMedia.media;

    @Override
    public List<AllBrandMediaListsResponseDto> getAllBrandMediaLists(String brandCode) {

        return jpaQueryFactory.select(new QAllBrandMediaListsResponseDto(
            media.mediaCode,
            media.url,
            media.mediaType.stringValue(),
            brandMediaList.isLogo))
            .from(brandMediaList)
            .rightJoin(media).on(brandMediaList.mediaCode.eq(media.mediaCode))
            .where(brandMediaList.brandCode.eq(brandCode))
            .orderBy(media.id.asc())
            .fetch();
    }

    @Override
    public String getBrandLogoUrl(String brandCode) {

        BooleanExpression condition = Expressions.allOf(
            brandMediaList.brandCode.eq(brandCode),
            brandMediaList.isLogo.eq(true));

        return jpaQueryFactory.select(media.url)
            .from(brandMediaList)
            .rightJoin(media).on(brandMediaList.mediaCode.eq(media.mediaCode))
            .where(condition)
            .fetchOne();
    }

}
