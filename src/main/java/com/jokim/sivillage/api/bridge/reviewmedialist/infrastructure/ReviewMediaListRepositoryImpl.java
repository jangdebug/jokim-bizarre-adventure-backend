package com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.reviewmedialist.domain.QReviewMediaList;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.AllReviewMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.QAllReviewMediaListsResponseDto;
import com.jokim.sivillage.api.media.domain.QMedia;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReviewMediaListRepositoryImpl implements ReviewMediaListRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QReviewMediaList reviewMediaList = QReviewMediaList.reviewMediaList;
    private final QMedia media = QMedia.media;

    @Override
    public List<AllReviewMediaListsResponseDto> getAllReviewMediaLists(String reviewCode) {

        return jpaQueryFactory.select(new QAllReviewMediaListsResponseDto(
                media.mediaCode,
                media.url,
                media.mediaType.stringValue()
            ))
            .from(reviewMediaList)
            .rightJoin(media).on(reviewMediaList.mediaCode.eq(media.mediaCode))
            .where(reviewMediaList.reviewCode.eq(reviewCode))
            .orderBy(media.id.asc())
            .fetch();
    }

}
