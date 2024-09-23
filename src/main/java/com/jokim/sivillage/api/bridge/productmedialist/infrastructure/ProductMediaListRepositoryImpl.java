package com.jokim.sivillage.api.bridge.productmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.productmedialist.domain.QProductMediaList;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.ProductMediaListResponseDto;
import com.jokim.sivillage.api.bridge.productmedialist.dto.out.QProductMediaListResponseDto;
import com.jokim.sivillage.api.media.domain.QMedia;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductMediaListRepositoryImpl implements ProductMediaListRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QProductMediaList productMediaList = QProductMediaList.productMediaList;
    private final QMedia media = QMedia.media;

    @Override
    public List<ProductMediaListResponseDto> getProductMediaList(String productCode) {

        return jpaQueryFactory.select(new QProductMediaListResponseDto(
                media.mediaCode
                , media.url
                , media.name
                , media.mediaType.stringValue()
                , productMediaList.isThumbnail))
            .from(productMediaList)
            .leftJoin(media).on(productMediaList.mediaCode.eq(media.mediaCode))
            .where(productMediaList.productCode.eq(productCode))
            .fetch();
    }

}
