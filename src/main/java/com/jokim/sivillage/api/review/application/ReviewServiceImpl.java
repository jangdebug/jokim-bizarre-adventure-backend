package com.jokim.sivillage.api.review.application;

import com.jokim.sivillage.api.bridge.reviewmedialist.domain.ReviewMediaList;
import com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure.ReviewMediaListRepository;
import com.jokim.sivillage.api.media.domain.Media;
import com.jokim.sivillage.api.media.infrastructure.MediaRepository;
import com.jokim.sivillage.api.review.domain.EvaluationItemName;
import com.jokim.sivillage.api.review.domain.EvaluationItemValue;
import com.jokim.sivillage.api.review.domain.ProductEvaluationManage;
import com.jokim.sivillage.api.review.domain.Review;
import com.jokim.sivillage.api.review.dto.in.ReviewRequestDto;
import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto;
import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto.Image;
import com.jokim.sivillage.api.review.infrastructure.EvaluationItemNameRepository;
import com.jokim.sivillage.api.review.infrastructure.EvaluationItemValueRepository;
import com.jokim.sivillage.api.review.infrastructure.ProductEvaluationManageRepository;
import com.jokim.sivillage.api.review.infrastructure.ReviewRepository;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
