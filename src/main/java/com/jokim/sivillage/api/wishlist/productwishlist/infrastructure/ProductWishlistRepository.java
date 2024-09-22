package com.jokim.sivillage.api.wishlist.productwishlist.infrastructure;

import com.jokim.sivillage.api.wishlist.productwishlist.domain.ProductWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductWishlistRepository extends JpaRepository<ProductWishlist, Long> {

    Optional<ProductWishlist> findByUuidAndProductCode(String uuid, String productCode);

}
