package com.jokim.sivillage.common.jwt;

import com.jokim.sivillage.api.customer.application.AuthCustomerDetailService;
import com.jokim.sivillage.common.redis.TokenBlacklistRepository;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //    todo JwtTokenProvider
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthCustomerDetailService userDetailsService;
    private final TokenBlacklistRepository tokenBlacklistRepository;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String uuid;
        log.info("Authorization : {}" , authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        // 블랙리스트에 있는 토큰인지 확인
        if (tokenBlacklistRepository.isBlacklisted(jwt)) {
            log.warn("블랙리스트에 있는 토큰: {}", jwt);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "블랙리스트에 있는 토큰입니다.");
            return;
        }

        uuid = Jwts.parser().verifyWith((SecretKey) jwtTokenProvider.getSignKey())
                .build().parseSignedClaims(jwt).getPayload().get("uuid", String.class);

        log.info("uuid: {}", uuid);

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(uuid);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}