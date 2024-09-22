package com.jokim.sivillage.api.review.application;

import com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure.ReviewMediaListRepository;
import com.jokim.sivillage.api.media.domain.Media;
import com.jokim.sivillage.api.media.infrastructure.MediaRepository;
import com.jokim.sivillage.api.review.domain.*;
import com.jokim.sivillage.api.review.dto.in.ReviewRequestDto;
import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto;
import com.jokim.sivillage.api.review.dto.out.ReviewSummaryResponseDto;
import com.jokim.sivillage.api.review.infrastructure.*;
import com.jokim.sivillage.api.review.vo.out.ReviewSummaryResponseVo;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ProductEvaluationManageRepository productEvaluationManageRepository;
    private final EvaluationItemNameRepository evaluationItemNameRepository;
    private final EvaluationItemValueRepository evaluationItemValueRepository;
    private final ReviewMediaListRepository reviewMediaListRepository;
    private final ProductStatisticRepository productStatisticRepository;
    private final MediaRepository mediaRepository;
    private final ReviewRepository reviewRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void createReview(ReviewRequestDto reviewRequestDto) {
        // 여기 uuid 가져오는거 수정 필요할수도 예외처리가 안됨
        String uuid = "1";
        if (uuid == null) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            reviewRepository.save(reviewRequestDto.toEntity(uuid));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ReviewSummaryResponseDto getReviewSummary(String productCode) {
        Double starAverage = getStarAverage(productCode);  // 별점 평균 가져오기
        List<ReviewSummaryResponseDto.EvaluationSummary> evaluations = getEvaluationSummaries(productCode); // 평가 항목 요약 가져오기
        List<String> images = getImageSummaries(productCode); // 이미지 요약 가져오기

        return ReviewSummaryResponseDto.of(starAverage, evaluations, images); // Dto에서 빌더 패턴 사용
    }

    // 별점 평균 가져오는 메서드
    private Double getStarAverage(String productCode) {
        ProductStatistic productStatistic = productStatisticRepository.findByProductCode(productCode);
        return (productStatistic != null) ? productStatistic.getStarAverage() : 0.0; // 없으면 0.0 반환
    }

    // 평가 항목 요약 가져오기
    private List<ReviewSummaryResponseDto.EvaluationSummary> getEvaluationSummaries(String productCode) {
        return productEvaluationManageRepository.findByProductCode(productCode).stream()
                .map(evaluation -> {
                    String value = evaluationItemValueRepository.findFirstByEvaluationItemNameId(evaluation.getEvaluationItemName().getId())
                            .map(EvaluationItemValue::getValue)
                            .orElse("N/A"); // 값이 없을 경우 기본값 설정

                    return ReviewSummaryResponseDto.EvaluationSummary.builder()
                            .name(evaluation.getEvaluationItemName().getName())
                            .value(value)
                            .rate(0.0) // 기본 rate
                            .build();
                })
                .toList();
    }

    // 이미지 요약 리스트 가져오기
    private List<String> getImageSummaries(String productCode) {
        return reviewRepository.findByProductCode(productCode).stream()
                .flatMap(review -> reviewMediaListRepository.findByReviewCode(review.getReviewCode()).stream()
                        .map(reviewMedia -> mediaRepository.findByMediaCode(reviewMedia.getMediaCode())
                                .map(Media::getUrl)
                                .orElse(""))) // 이미지 URL이 없으면 빈 문자열 반환
                .toList();
    }

    @Override
    public List<ReviewResponseDto> getReview(String productCode) {
        List<Review> reviews = reviewRepository.findByProductCode(productCode);

        return reviews.stream().map(review -> ReviewResponseDto.fromReview(
                        review,
                        getReviewImages(review.getReviewCode()),  // 이미지 가져오기
                        getEvaluations(productCode)               // 평가 항목 가져오기
                )
        ).toList();
    }

    private List<String> getReviewImages(String reviewCode) {
        return reviewMediaListRepository.findByReviewCode(reviewCode).stream()
                .map(reviewMedia -> mediaRepository.findByMediaCode(reviewMedia.getMediaCode())
                        .orElseThrow(() -> new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR))
                        .getUrl())
                .toList();
    }

    public List<ReviewResponseDto.Evaluation> getEvaluations(String productCode) {
        return productEvaluationManageRepository.findByProductCode(productCode).stream()
                .flatMap(evaluationManage -> evaluationItemValueRepository
                        .findByEvaluationItemNameId(evaluationManage.getEvaluationItemName().getId()).stream()
                        .map(value -> ReviewResponseDto.Evaluation.builder()
                                .name(evaluationManage.getEvaluationItemName().getName())
                                .value(value.getValue())
                                .build())
                )
                .toList();
    }

}
