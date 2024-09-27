package com.jokim.sivillage.api.review.application;

import com.jokim.sivillage.api.batch.domain.ReviewStatistic;
import com.jokim.sivillage.api.batch.infrastructure.ReviewStatisticRepository;
import com.jokim.sivillage.api.review.domain.*;
import com.jokim.sivillage.api.review.dto.out.CustomerReviewDetailDto;
import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto;
import com.jokim.sivillage.api.review.dto.out.ReviewSummaryResponseDto;
import com.jokim.sivillage.api.review.infrastructure.*;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import java.util.List;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ProductEvaluationManageRepository productEvaluationManageRepository;
    private final EvaluationItemNameRepository evaluationItemNameRepository;
    private final EvaluationItemValueRepository evaluationItemValueRepository;
    private final ProductStatisticRepository productStatisticRepository;
    private final ProductStarAverageRepository productStarAverageRepository;
    private final ReviewStatisticRepository reviewStatisticRepository;
    private final ReviewRepository reviewRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public ReviewSummaryResponseDto getReviewSummary(String productCode) {
        // 1. ProductStatistic 리스트를 productCode로 조회
        List<ProductStatistic> productStatistics = productStatisticRepository.findByProductCode(productCode);

        // 2. 각각의 ProductStatistic에서 nameId, valueId를 이용해 EvaluationItemName과 EvaluationItemValue 정보 조회
        List<ReviewSummaryResponseDto.EvaluationSummary> evaluationSummaries = productStatistics.stream()
            .map(productStatistic -> {
                // EvaluationItemName에서 name 가져오기
                EvaluationItemName evaluationItemName = evaluationItemNameRepository.findById(productStatistic.getEvaluationItemNameId())
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_REVIEWSTATISTIC_NAME));

                // EvaluationItemValue에서 value 가져오기
                EvaluationItemValue evaluationItemValue = evaluationItemValueRepository.findById(productStatistic.getEvaluationItemValueId())
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_REVIEWSTATISTIC_VALUE));

                // 3. 각각의 name, value, rate 정보를 EvaluationSummary 객체로 변환
                return ReviewSummaryResponseDto.EvaluationSummary.builder()
                    .name(evaluationItemName.getName())
                    .value(evaluationItemValue.getValue())
                    .rate(productStatistic.getEvaluationItemNameRate())
                    .build();
            })
            .collect(Collectors.toList());

        // 4. ProductStarAverage에서 별점 평균 조회
        ProductStarAverage starAverage = productStarAverageRepository.findByProductCode(productCode);

        // 5. ReviewSummaryResponseDto 객체 생성 후 반환
        return ReviewSummaryResponseDto.of(starAverage.getStarPoint(), evaluationSummaries);
    }

    @Override
    public Integer getProductReviewCount(String productCode) {
        return reviewRepository.countByProductCode(productCode);
    }

    @Override
    public Integer getCustomerReviewCount(String accessToken) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(accessToken);
        return reviewRepository.countByUuid(uuid);
    }

    @Override
    public List<CustomerReviewDetailDto> getCustomerReviewDetail(String accessToken) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(accessToken);

        return reviewRepository.findByUuid(uuid).stream().map(CustomerReviewDetailDto::toDto).toList();
    }


    @Transactional
    @Override
    public Page<ReviewResponseDto> getReview(String productCode, Pageable pageable) {
        // productCode에 해당하는 Review를 페이징 처리하여 가져옴
        Page<Review> reviews = reviewRepository.findByProductCode(productCode, pageable);

        // Page 객체의 map() 메서드를 사용하여 Review를 ReviewResponseDto로 변환
        return reviews.map(review -> {
            // 각 리뷰에 대한 통계 정보 가져오기
            ReviewStatistic reviewStatistics = reviewStatisticRepository.findByReviewCode(review.getReviewCode());

            // 각 리뷰에 대한 평가 항목 리스트 가져오기
            List<ReviewResponseDto.Evaluation> evaluations = getEvaluations(review.getReviewCode());

            // ReviewResponseDto를 생성하여 반환
            return ReviewResponseDto.fromReview(review, reviewStatistics, evaluations);
        });
    }

    public List<ReviewResponseDto.Evaluation> getEvaluations(String reviewCode) {
        return evaluationItemValueRepository.findByReviewCode(reviewCode).stream()
            .map(value -> ReviewResponseDto.Evaluation.builder()
                .name(value.getEvaluationItemName().getName()) // 평가 항목 이름 가져오기
                .value(value.getValue()) // 평가 항목 값 가져오기
                .isBest(value.getIsBest()) // isBest 처리
                .build())
            .toList();
    }

}

