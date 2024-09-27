package com.jokim.sivillage.api.wishlist.eventwishlist.infrastructure;

import com.jokim.sivillage.api.wishlist.eventwishlist.domain.EventWishlist;
import com.jokim.sivillage.api.wishlist.eventwishlist.domain.QEventWishlist;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class EventWishlistRepositoryImpl implements EventWishlistRepositoryCustom {

    private final QEventWishlist eventWishlist = QEventWishlist.eventWishlist;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<EventWishlist> getAllEventWishlists(String uuid, Integer recentMonths) {

        LocalDateTime currentDate = LocalDateTime.now();
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(eventWishlist.uuid.eq(uuid));

        // 최근 몇 달 필터링
        Optional.ofNullable(recentMonths).ifPresent(
            months -> builder.and(eventWishlist.updatedAt.gt(currentDate.minusMonths(months))));

        builder.and(eventWishlist.isChecked.eq(true));

        return jpaQueryFactory.selectFrom(eventWishlist)
            .where(builder)
            .orderBy(eventWishlist.updatedAt.desc())
            .fetch();

    }

}
