package com.jokim.sivillage.api.review.dto.out;

import com.jokim.sivillage.api.review.domain.Review;
import com.jokim.sivillage.api.review.vo.out.CustomerReviewDetailVo;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class CustomerReviewDetailDto {
    private String productCode;
    private String reviewCode;
    private String content;
    private LocalDateTime created_at;


    public static CustomerReviewDetailDto toDto(Review review){
        return CustomerReviewDetailDto.builder()
            .productCode(review.getProductCode())
            .reviewCode(review.getReviewCode())
            .content(review.getContent())
            .created_at(review.getCreatedAt())
            .build();
    }

    public CustomerReviewDetailVo toVo(){
        return CustomerReviewDetailVo.builder()
            .productCode(productCode)
            .content(content)
            .created_at(created_at)
            .reviewCode(reviewCode)
            .build();
    }
}
