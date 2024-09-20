package com.jokim.sivillage.api.brand.application;

import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_GENERATE_BRAND_CODE;

import com.jokim.sivillage.api.brand.dto.BrandRequestDto;
import com.jokim.sivillage.api.brand.infrastructure.BrandRepository;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private static final int MAX_CODE_TRIES = 5;

    @Transactional
    @Override
    public void addBrand(BrandRequestDto brandRequestDto) {
        String brandCode = generateUniqueBrandCode();

        brandRepository.save(brandRequestDto.toEntity(brandCode));
    }

    private String generateUniqueBrandCode() {
        for(int i = 0; i < MAX_CODE_TRIES; i++) {
            String brandCode = CodeGenerator.generateCode("BR");

            if(!brandRepository.existsByBrandCode(brandCode)) return brandCode;
        }

        throw new BaseException(FAILED_TO_GENERATE_BRAND_CODE);
    }

}
