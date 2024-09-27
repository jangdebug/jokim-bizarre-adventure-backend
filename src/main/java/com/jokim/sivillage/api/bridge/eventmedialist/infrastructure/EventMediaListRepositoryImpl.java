package com.jokim.sivillage.api.bridge.eventmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.eventmedialist.domain.QEventMediaList;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.AllEventMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.QAllEventMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.QThumbnailEventMediaListResponseDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.ThumbnailEventMediaListResponseDto;
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
public class EventMediaListRepositoryImpl implements EventMediaListRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QEventMediaList eventMediaList = QEventMediaList.eventMediaList;
    private final QMedia media = QMedia.media;

    @Override
    public List<AllEventMediaListsResponseDto> getAllEventMediaLists(String eventCode) {
        return jpaQueryFactory.select(new QAllEventMediaListsResponseDto(
            media.mediaCode,
            media.url,
            media.mediaType.stringValue(),
            eventMediaList.isThumbnail))
            .from(eventMediaList)
            .rightJoin(media).on(eventMediaList.mediaCode.eq(media.mediaCode))
            .where(eventMediaList.eventCode.eq(eventCode))
            .orderBy(media.id.asc())
            .fetch();
    }

    @Override
    public Optional<ThumbnailEventMediaListResponseDto> getThumbnailByEventCode(String eventCode) {

        BooleanExpression condition = Expressions.allOf(
            eventMediaList.eventCode.eq(eventCode),
            eventMediaList.isThumbnail.eq(true));

        return Optional.ofNullable(jpaQueryFactory.select(new QThumbnailEventMediaListResponseDto(
                media.mediaCode,
                media.url,
                media.mediaType.stringValue()))
            .from(eventMediaList)
            .rightJoin(media).on(eventMediaList.mediaCode.eq(media.mediaCode))
            .where(condition)
            .fetchOne());

    }

}
