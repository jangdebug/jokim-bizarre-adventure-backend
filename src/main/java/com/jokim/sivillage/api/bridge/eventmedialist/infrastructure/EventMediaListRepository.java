package com.jokim.sivillage.api.bridge.eventmedialist.infrastructure;

import com.jokim.sivillage.api.bridge.eventmedialist.domain.EventMediaList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventMediaListRepository extends JpaRepository<EventMediaList, Long> {

    Optional<EventMediaList> findByEventCodeAndMediaCode(String eventCode, String mediaCode);

    List<EventMediaList> findByEventCodeAndIsThumbnail(String eventCode, Boolean isThumbnail);
    List<EventMediaList> findByEventCode(String eventCode);

    boolean existsByEventCodeAndIsThumbnail(String eventCode, Boolean isThumbnail);

}
