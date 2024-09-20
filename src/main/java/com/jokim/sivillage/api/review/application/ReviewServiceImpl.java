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
public class ReviewServiceImpl implements ReviewService{

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
        if(uuid == null){
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            reviewRepository.save(reviewRequestDto.toEntity(uuid));
        }catch(Exception e){
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // todo 버전1 getReview
//    @Override
//    public List<ReviewResponseDto> getReview(String productCode) {
//        // 1. Product에 대한 리뷰 목록을 가져옴
//        List<Review> reviews = reviewRepository.findByProductCode(productCode);
//
//        if (reviews.isEmpty()) {
//            throw new BaseException(BaseResponseStatus.NO_EXIST_REVIEW);
//        }
//
//        // 2. 각 Review에 대해 관련 미디어 리스트와 평가 가져오기
//        return reviews.stream().map(review -> {
//            List<ReviewResponseDto.Image> images = getReviewImages(review.getReviewCode());
//            List<ReviewResponseDto.Evaluation> evaluations = getEvaluations(productCode);
//
//            // DTO의 정적 팩토리 메서드 호출
//            return ReviewResponseDto.fromReview(review, images, evaluations);
//        }).toList();
//    }
//
//    private List<ReviewResponseDto.Image> getReviewImages(String reviewCode) {
//        return reviewMediaListRepository.findByReviewCode(reviewCode)
//            .stream()
//            .map(reviewMediaList -> {
//                Media media = mediaRepository.findByMediaCode(reviewMediaList.getMediaCode())
//                    .orElseThrow(() -> new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR));
//
//                return ReviewResponseDto.Image.builder()
//                    .url(media.getUrl())
//                    .build();
//            })
//            .toList();
//    }
//
//    public List<ReviewResponseDto.Evaluation> getEvaluations(String productCode) {
//        return productEvaluationManageRepository.findByProductCode(productCode)
//            .stream()
//            .flatMap(evaluationManage -> {
//                List<EvaluationItemValue> values = evaluationItemValueRepository.findByEvaluationItemNameId(evaluationManage.getEvaluationItemName().getId());
//                return values.stream().map(value -> {
//                    EvaluationItemName itemName = evaluationItemNameRepository.findById(value.getEvaluationItemName().getId())
//                        .orElseThrow(() -> new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR));
//
//                    return ReviewResponseDto.Evaluation.builder()
//                        .name(itemName.getName())
//                        .value(value.getValue())
//                        .build();
//                });
//            }).toList();
//    }

    //todo 버전2 getReView
@Override
public List<ReviewResponseDto> getReview(String productCode) {
    // 1. 리뷰 목록을 한 번에 가져오기
    List<Review> reviews = reviewRepository.findByProductCode(productCode);

//    if (reviews.isEmpty()) {
//        throw new BaseException(BaseResponseStatus.NO_EXIST_REVIEW);
//    }

//    // 2. 리뷰 코드 리스트 추출
//    List<String> reviewCodes = reviews.stream()
//        .map(Review::getReviewCode)
//        .collect(Collectors.toList());


    return reviews.stream().map(review -> ReviewResponseDto.fromReview(
              review,
              reviewMediaListRepository.findByReviewCode(review.getReviewCode()).stream().map(
                  media -> mediaRepository.findByMediaCode(media.getMediaCode()).get().getUrl()
              ).toList()
              ,
              getEvaluations(productCode)
              )
    ).toList();




//    // 3. 리뷰 코드에 해당하는 모든 미디어 리스트를 한 번에 가져오기
//    Map<String, List<Image>> reviewImagesMap = getReviewImages(reviewCodes);
//
//    // 4. productCode에 해당하는 평가 정보를 한 번에 가져오기
//    List<ReviewResponseDto.Evaluation> evaluations = getEvaluations(productCode);

    // 5. 리뷰 데이터를 DTO로 변환
//    return reviews.stream()
//        .map(review -> ReviewResponseDto.builder()
//            .productCode(review.getProductCode())
//            .reviewCode(review.getReviewCode())
//            .starPoint(review.getStarPoint())
//            .type(review.getType())
//            .isBest(false) // 기본값 설정
//            .customerEmail("anonymous") // 익명 처리
//            .modifyDate(review.getUpdatedAt()) // 수정 날짜
//            .likeCount(0) // 좋아요 수
//            .productOption("default") // 기본값 설정
//            .content(review.getContent())
//            .evaluation(evaluations) // 평가 정보 추가
//            .image(reviewImagesMap.getOrDefault(review.getReviewCode(), List.of())) // 이미지 정보 추가
//            .build())
//        .toList();
}

    private Map<String, List<ReviewResponseDto.Image>> getReviewImages(List<String> reviewCodes) {
        // 한 번의 쿼리로 모든 리뷰 코드에 대한 미디어 리스트를 가져옴
        List<ReviewMediaList> mediaLists = reviewMediaListRepository.findByReviewCodeIn(reviewCodes);

        // 미디어 코드를 한 번에 조회
        List<String> mediaCodes = mediaLists.stream()
            .map(ReviewMediaList::getMediaCode)
            .collect(Collectors.toList());
        List<Media> mediaList = mediaRepository.findByMediaCodeIn(mediaCodes);

        // Media 정보를 Map으로 변환
        Map<String, Media> mediaMap = mediaList.stream()
            .collect(Collectors.toMap(Media::getMediaCode, media -> media));

        // 리뷰별로 이미지를 Map으로 그룹화
        return mediaLists.stream()
            .collect(Collectors.groupingBy(
                ReviewMediaList::getReviewCode,
                Collectors.mapping(reviewMediaList -> ReviewResponseDto.Image.builder()
                        .url(mediaMap.get(reviewMediaList.getMediaCode()).getUrl())
                        .build(),
                    Collectors.toList())
            ));
    }

    public List<ReviewResponseDto.Evaluation> getEvaluations(String productCode) {
        // 한 번의 쿼리로 평가 정보를 가져오기
        List<ProductEvaluationManage> evaluationManages = productEvaluationManageRepository.findByProductCode(productCode);

        List<Long> evaluationItemNameIds = evaluationManages.stream()
            .map(evaluationManage -> evaluationManage.getEvaluationItemName().getId())
            .collect(Collectors.toList());

        // 평가 항목에 대한 값들을 한 번에 조회
        List<EvaluationItemValue> values = evaluationItemValueRepository.findByEvaluationItemNameIdIn(evaluationItemNameIds);

        // 평가 항목 이름을 조회하여 Map으로 변환
        Map<Long, EvaluationItemName> itemNameMap = evaluationItemNameRepository.findByIdIn(evaluationItemNameIds)
            .stream()
            .collect(Collectors.toMap(EvaluationItemName::getId, item -> item));

        // 평가 정보를 DTO로 변환
        return values.stream()
            .map(value -> ReviewResponseDto.Evaluation.builder()
                .name(itemNameMap.get(value.getEvaluationItemName().getId()).getName())
                .value(value.getValue())
                .build())
            .toList();
    }

}
