package com.jokim.sivillage.api.wishlist.eventwishlist.infrastructure;

import com.jokim.sivillage.api.wishlist.eventwishlist.domain.EventWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventWishlistRepository extends JpaRepository<EventWishlist, Long> {

    Optional<EventWishlist> findByUuidAndEventCode(String uuid, String eventCode);

    boolean existsByUuidAndEventCodeAndIsChecked(String uuid, String eventCode, Boolean isChecked);

}
