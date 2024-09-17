package com.jokim.sivillage.api.media.infrastructure;

import com.jokim.sivillage.api.media.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {

    boolean existsByMediaCode(String mediaCode);

}
