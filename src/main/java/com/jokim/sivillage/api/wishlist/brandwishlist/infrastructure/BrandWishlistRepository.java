package com.jokim.sivillage.api.wishlist.brandwishlist.infrastructure;

import com.jokim.sivillage.api.wishlist.brandwishlist.domain.BrandWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandWishlistRepository extends JpaRepository<BrandWishlist, Long> {

    Optional<BrandWishlist> findByUuidAndBrandCode(String uuid, String brandCode);

}
