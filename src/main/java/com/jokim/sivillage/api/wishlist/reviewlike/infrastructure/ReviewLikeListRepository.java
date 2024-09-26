package com.jokim.sivillage.api.wishlist.reviewlike.infrastructure;

import com.jokim.sivillage.api.wishlist.reviewlike.domain.ReviewLikeList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikeListRepository extends JpaRepository<ReviewLikeList, Long> {

    Optional<ReviewLikeList> findByUuidAndReviewCode(String uuid, String reviewCode);

    boolean existsByUuidAndReviewCodeAndIsChecked(String uuid, String reviewCode, Boolean isChecked);

}
