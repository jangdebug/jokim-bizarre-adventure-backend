package com.jokim.sivillage.api.best.product.application;

import com.jokim.sivillage.api.best.product.domain.BestProduct;
import com.jokim.sivillage.api.best.product.dto.BestProductResponseDto;
import com.jokim.sivillage.api.best.product.infrastructure.BestProductRepository;
import com.jokim.sivillage.api.best.product.vo.BestProductResponseVo;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class BestProductServiceImpl implements BestProductService{

    private final BestProductRepository bestProductRepository;

    @Override
    public List<BestProductResponseDto> getBestProduct() {
        List<BestProduct> bestProducts = bestProductRepository.findAll();

        // rankValue 기준으로 오름차순 정렬
        return bestProducts.stream()
            .sorted(Comparator.comparing(BestProduct::getRankValue)) // rankValue 기준 오름차순 정렬
            .map(BestProductResponseDto::toDto) // DTO로 변환
            .toList();
    }
}
