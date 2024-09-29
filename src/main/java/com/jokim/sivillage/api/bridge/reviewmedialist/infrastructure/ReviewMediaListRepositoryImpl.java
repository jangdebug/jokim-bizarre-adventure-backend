package com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.reviewmedialist.domain.QReviewMediaList;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.AllReviewMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.reviewmedialist.dto.out.QAllReviewMediaListsResponseDto;
import com.jokim.sivillage.api.media.domain.QMedia;
import com.jokim.sivillage.api.review.domain.QReview;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReviewMediaListRepositoryImpl implements ReviewMediaListRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QReview review = QReview.review;
    private final QReviewMediaList reviewMediaList = QReviewMediaList.reviewMediaList;
    private final QMedia media = QMedia.media;

    @Override
    public List<AllReviewMediaListsResponseDto> getAllReviewMediaLists(String reviewCode) {

        return jpaQueryFactory.select(new QAllReviewMediaListsResponseDto(
                media.mediaCode,
                media.url,
                media.mediaType.stringValue()))
            .from(reviewMediaList)
            .rightJoin(media).on(reviewMediaList.mediaCode.eq(media.mediaCode))
            .where(reviewMediaList.reviewCode.eq(reviewCode))
            .orderBy(media.id.asc())
            .fetch();
    }

    @Override
    public List<AllReviewMediaListsResponseDto> getAllReviewMediaListsByProduct(String productCode, Integer fetchLimit) {

        List<String> reviewCodeList = jpaQueryFactory.select(review.reviewCode).from(review)
                .where(review.productCode.eq(productCode))
                .fetch();

        JPAQuery<AllReviewMediaListsResponseDto> query = jpaQueryFactory.select(new QAllReviewMediaListsResponseDto(
                media.mediaCode,
                media.url,
                media.mediaType.stringValue()))
                .from(reviewMediaList)
                .rightJoin(media).on(reviewMediaList.mediaCode.eq(media.mediaCode))
                .where(reviewMediaList.reviewCode.in(reviewCodeList))
                .orderBy(media.id.desc());

        if(fetchLimit != null) query.limit(fetchLimit);

        return query.fetch();

    }

}
