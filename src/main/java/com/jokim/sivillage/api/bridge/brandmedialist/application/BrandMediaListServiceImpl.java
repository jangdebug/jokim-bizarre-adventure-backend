package com.jokim.sivillage.api.bridge.brandmedialist.application;

import com.jokim.sivillage.api.bridge.brandmedialist.dto.BrandMediaListRequestDto;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.BrandMediaListResponseDto;
import com.jokim.sivillage.api.bridge.brandmedialist.infrastructure.BrandMediaListRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BrandMediaListServiceImpl implements BrandMediaListService {

    private final BrandMediaListRepository brandMediaListRepository;

    @Transactional
    @Override
    public void addBrandMediaList(BrandMediaListRequestDto brandMediaListRequestDto) {

        brandMediaListRepository.save(brandMediaListRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public List<BrandMediaListResponseDto> getBrandMediaList(String brandCode) {
        return brandMediaListRepository.findByBrandCode(brandCode)
            .stream().map(BrandMediaListResponseDto::toDto).toList();
    }

}
