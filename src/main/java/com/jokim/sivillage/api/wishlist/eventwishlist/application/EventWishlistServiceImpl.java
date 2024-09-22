package com.jokim.sivillage.api.wishlist.eventwishlist.application;

import com.jokim.sivillage.api.wishlist.eventwishlist.domain.EventWishlist;
import com.jokim.sivillage.api.wishlist.eventwishlist.dto.EventWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.eventwishlist.infrastructure.EventWishlistRepository;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EventWishlistServiceImpl implements EventWishlistService {

    private final EventWishlistRepository eventWishlistRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public void addEventWishlist(EventWishlistRequestDto eventWishlistRequestDto) {

        String uuid = jwtTokenProvider.validateAndGetUserUuid(eventWishlistRequestDto.getAccessToken());
        Long id =eventWishlistRepository.findByUuidAndEventCode(uuid, eventWishlistRequestDto.getEventCode())
                .map(EventWishlist::getId).orElse(null);

        eventWishlistRepository.save(eventWishlistRequestDto.toEntity(id, uuid, true));
    }

}
