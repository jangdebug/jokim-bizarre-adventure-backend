package com.jokim.sivillage.api.bridge.brandmedialist.application;

import com.jokim.sivillage.api.bridge.brandmedialist.dto.in.BrandMediaListRequestDto;
import com.jokim.sivillage.api.bridge.brandmedialist.dto.out.AllBrandMediaListsResponseDto;
import com.jokim.sivillage.api.bridge.brandmedialist.infrastructure.BrandMediaListRepository;
import com.jokim.sivillage.api.bridge.brandmedialist.infrastructure.BrandMediaListRepositoryCustom;
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
    private final BrandMediaListRepositoryCustom brandMediaListRepositoryCustom;

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
    public List<AllBrandMediaListsResponseDto> getAllBrandMediaLists(String brandCode) {
        return brandMediaListRepositoryCustom.getAllBrandMediaLists(brandCode);
    }

}
