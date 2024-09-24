package com.jokim.sivillage.api.bridge.productcategorylist.infrastructure;

import com.jokim.sivillage.api.bridge.productcategorylist.domain.ProductCategoryList;
import com.jokim.sivillage.api.bridge.productcategorylist.domain.QProductCategoryList;
import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.common.utils.CursorPage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductCategoryListRepositoryImpl implements ProductCategoryListRepositoryCustom {

    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    private final JPAQueryFactory jpaQueryFactory;
    private final QProductCategoryList productCategoryList = QProductCategoryList.productCategoryList;


    @Override
    public CursorPage<String> getProductCategoryListByCategories(
        String mainCategoryCode, String secondaryCategoryCode,
        String tertiaryCategoryCode, String quaternaryCategoryCode,
        Long lastId, Integer pageSize, Integer pageNo) {

        BooleanBuilder builder = new BooleanBuilder();

        // 카테고리별 필터 적용
        Optional.ofNullable(mainCategoryCode)
            .ifPresent(code -> builder.and(productCategoryList.mainCategoryCode.eq(code)));

        Optional.ofNullable(secondaryCategoryCode)
            .ifPresent(code -> builder.and(productCategoryList.secondaryCategoryCode.eq(code)));

        Optional.ofNullable(tertiaryCategoryCode)
            .ifPresent(code -> builder.and(productCategoryList.tertiaryCategoryCode.eq(code)));

        Optional.ofNullable(quaternaryCategoryCode)
            .ifPresent(code -> builder.and(productCategoryList.quaternaryCategoryCode.eq(code)));

        // 판매 중인가
        builder.and(productCategoryList.isOnSale.eq(true));

        // 마지막 ID 커서 적용
        Optional.ofNullable(lastId)
            .ifPresent(id -> builder.and(productCategoryList.id.lt(id)));

        // 페이지 넘버와 페이지 크기 기본값 설정
        int curPageNo = Optional.ofNullable(pageNo).orElse(DEFAULT_PAGE_NUMBER);
        int curPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

        // offset 계산
        int offset = curPageNo == 0 ? 0 : (curPageNo - 1) * curPageSize;

        // Data Fetching (pageSize + 1로 가져와서 다음 페이지 확인)
        List<ProductCategoryList> content = jpaQueryFactory
            .selectFrom(productCategoryList)
            .where(builder)
            .orderBy(productCategoryList.id.desc())
            .offset(offset)
            .limit(curPageSize + 1)
            .fetch();

        // 다음 페이지 커서 처리 및 hasNext 여부 판단
        Long nextCursor = null;
        boolean hasNext = false;

        if (content.size() > curPageSize) {
            hasNext = true;
            nextCursor = content.get(curPageSize).getId();  // 마지막 항목의 ID를 커서로 설정
            content = content.subList(0, curPageSize);      // 실제 페이지 사이즈 만큼 자르기
        }

        List<String> productCodeList = content.stream()
            .map(ProductCategoryList::getProductCode).toList();

        return new CursorPage<>(productCodeList, nextCursor, hasNext, pageSize, pageNo);

    }

    @Override
    public List<Long> findByProductCodeAndCategoryCodes(ProductCategoryListRequestDto requestDto) {

        BooleanBuilder condition = new BooleanBuilder();

        Optional.ofNullable(requestDto.getProductCode())
            .ifPresent(code -> condition.and(productCategoryList.productCode.eq(code)));

        Optional.ofNullable(requestDto.getMainCategoryCode())
            .ifPresent(code -> condition.and(productCategoryList.mainCategoryCode.eq(code)));

        Optional.ofNullable(requestDto.getSecondaryCategoryCode())
            .ifPresentOrElse(code -> condition.and(productCategoryList.secondaryCategoryCode.eq(code)),
                () -> condition.and(productCategoryList.secondaryCategoryCode.isNull()));

        Optional.ofNullable(requestDto.getTertiaryCategoryCode())
            .ifPresentOrElse(code -> condition.and(productCategoryList.tertiaryCategoryCode.eq(code)),
                () -> condition.and(productCategoryList.tertiaryCategoryCode.isNull()));

        Optional.ofNullable(requestDto.getQuaternaryCategoryCode())
            .ifPresentOrElse(code -> condition.and(productCategoryList.quaternaryCategoryCode.eq(code)),
                () -> condition.and(productCategoryList.quaternaryCategoryCode.isNull()));

        return jpaQueryFactory.select(productCategoryList.id)
            .from(productCategoryList)
            .where(condition)
            .fetch();
    }

}
