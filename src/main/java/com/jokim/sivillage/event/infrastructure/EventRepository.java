package com.jokim.sivillage.event.infrastructure;

import com.jokim.sivillage.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByOrderByIdDesc();


}
