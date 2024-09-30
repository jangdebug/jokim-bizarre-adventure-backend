package com.jokim.sivillage.api.trending.mostViewProduct.presentation;

import com.jokim.sivillage.api.trending.mostViewProduct.application.MostViewProductService;
import com.jokim.sivillage.api.trending.mostViewProduct.dto.MostViewProductDto;
import com.jokim.sivillage.api.trending.mostViewProduct.vo.MostViewProductVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class MostViewProductController {

    private final MostViewProductService mostViewProductService;

    @Operation(summary = "Statistics API", description = "MostViewProduct API 입니다.", tags = {"Statistics"})
    @GetMapping("/most-view-product")
    public BaseResponse<List<MostViewProductVo>> getMostViewProduct() {
        return new BaseResponse<>(mostViewProductService.getMostViewProduct().stream().map(
            MostViewProductDto::toVo).toList());
    }

}