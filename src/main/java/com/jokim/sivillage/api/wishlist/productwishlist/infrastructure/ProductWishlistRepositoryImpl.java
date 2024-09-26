package com.jokim.sivillage.api.wishlist.productwishlist.infrastructure;

import com.jokim.sivillage.api.wishlist.productwishlist.domain.ProductWishlist;
import com.jokim.sivillage.api.wishlist.productwishlist.domain.QProductWishlist;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductWishlistRepositoryImpl implements ProductWishlistRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QProductWishlist productWishlist = QProductWishlist.productWishlist;

    @Override
    public List<ProductWishlist> getAllProductWishlists(String uuid, Integer recentMonths) {

        LocalDateTime currentDate = LocalDateTime.now();
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(productWishlist.uuid.eq(uuid));

        // 최근 몇 달 필터링
        Optional.ofNullable(recentMonths).ifPresent(
            months -> builder.and(productWishlist.updatedAt.gt(currentDate.minusMonths(months))));

        // 체크 상태인가
        builder.and(productWishlist.isChecked.eq(true));

        return jpaQueryFactory.selectFrom(productWishlist)
            .where(builder)
            .orderBy(productWishlist.updatedAt.desc())
            .fetch();

    }
}
