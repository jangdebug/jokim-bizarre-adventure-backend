package com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.reviewmedialist.domain.ReviewMediaList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewMediaListRepository extends JpaRepository<ReviewMediaList, Long> {

    List<ReviewMediaList> findByReviewCodeOrderById(String reviewCode);

}
