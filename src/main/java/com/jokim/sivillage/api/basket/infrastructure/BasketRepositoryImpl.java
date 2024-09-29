package com.jokim.sivillage.api.basket.infrastructure;

import com.jokim.sivillage.api.basket.dto.out.ProductOptionInfoResponseDto;
import com.jokim.sivillage.api.product.domain.ProductOption;
import com.jokim.sivillage.api.product.domain.option.QColor;
import com.jokim.sivillage.api.product.domain.option.QSize;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BasketRepositoryImpl implements BasketRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSize size = QSize.size;
    private final QColor color = QColor.color;

    @Override
    public ProductOptionInfoResponseDto getProductOptionInfo(ProductOption productOption) {

        String colorValue = productOption.getColor() != null ? jpaQueryFactory.select(color.value)
                    .from(color)
                    .where(color.eq(productOption.getColor()))
                    .fetchOne() : "";

        String sizeValue = productOption.getSize() != null ? jpaQueryFactory.select(size.value)
                .from(size)
                .where(size.eq(productOption.getSize()))
                .fetchOne() : "";

        return ProductOptionInfoResponseDto.toDto(
                !colorValue.isEmpty() && !sizeValue.isEmpty() ?
                        colorValue + "/" + sizeValue : colorValue + sizeValue);
    }

}
