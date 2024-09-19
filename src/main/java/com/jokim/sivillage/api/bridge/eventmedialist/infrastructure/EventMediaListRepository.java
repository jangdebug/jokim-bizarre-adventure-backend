package com.jokim.sivillage.api.bridge.eventmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.eventmedialist.domain.EventMediaList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventMediaListRepository extends JpaRepository<EventMediaList, Long> {

    boolean existsByEventCodeAndIsThumbnail(String eventCode, Boolean isThumbnail);

}
