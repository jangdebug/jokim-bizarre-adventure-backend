package com.jokim.sivillage.api.bridge.brandmedialist.application;

import com.jokim.sivillage.api.bridge.brandmedialist.dto.BrandMediaListRequestDto;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.BrandMediaListResponseDto;
import com.jokim.sivillage.api.bridge.brandmedialist.infrastructure.BrandMediaListRepository;
import java.util.List;
import java.util.Optional;

import com.jokim.sivillage.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.ALREADY_EXIST_LOGO;

@RequiredArgsConstructor
@Service
public class BrandMediaListServiceImpl implements BrandMediaListService {

    private final BrandMediaListRepository brandMediaListRepository;

    @Transactional
    @Override
    public void addBrandMediaList(BrandMediaListRequestDto brandMediaListRequestDto) {
        Boolean isLogo = Optional.ofNullable(brandMediaListRequestDto.getIsLogo()).orElse(false);

        if(isLogo && brandMediaListRepository.
                existsByBrandCodeAndIsLogo(brandMediaListRequestDto.getBrandCode(), true))
            throw new BaseException(ALREADY_EXIST_LOGO);

        brandMediaListRepository.save(brandMediaListRequestDto.toEntity(isLogo));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BrandMediaListResponseDto> getBrandMediaList(String brandCode) {
        return brandMediaListRepository.findByBrandCode(brandCode)
            .stream().map(BrandMediaListResponseDto::toDto).toList();
    }

}
