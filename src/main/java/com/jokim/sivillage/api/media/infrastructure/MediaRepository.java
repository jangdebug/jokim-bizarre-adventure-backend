package com.jokim.sivillage.api.media.infrastructure;

import com.jokim.sivillage.api.media.domain.Media;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByMediaCode(String mediaCode);

    boolean existsByMediaCode(String mediaCode);

    void deleteByMediaCode(String mediaCode);

}
