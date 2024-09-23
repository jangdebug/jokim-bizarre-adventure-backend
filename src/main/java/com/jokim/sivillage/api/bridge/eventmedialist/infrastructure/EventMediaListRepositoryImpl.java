package com.jokim.sivillage.api.bridge.eventmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.eventmedialist.domain.QEventMediaList;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.AllEventMediaListResponseDto;
import com.jokim.sivillage.api.bridge.eventmedialist.dto.out.QAllEventMediaListResponseDto;
import com.jokim.sivillage.api.media.domain.QMedia;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class EventMediaListRepositoryImpl implements EventMediaListRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QEventMediaList eventMediaList = QEventMediaList.eventMediaList;
    private final QMedia media = QMedia.media;

    @Override
    public List<AllEventMediaListResponseDto> getAllEventMediaLists(String eventCode) {
        return jpaQueryFactory.select(new QAllEventMediaListResponseDto(
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
}
