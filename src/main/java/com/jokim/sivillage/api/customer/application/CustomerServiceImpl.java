package com.jokim.sivillage.api.customer.application;


import com.jokim.sivillage.api.customer.domain.*;
import com.jokim.sivillage.api.customer.dto.DuplicateEmailDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenRequestDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenResponseDto;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;
import com.jokim.sivillage.api.customer.entity.AuthUserDetail;
import com.jokim.sivillage.api.customer.infrastructure.*;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.common.redis.TokenBlacklistRepository;
import com.jokim.sivillage.common.redis.TokenRedis;
import com.jokim.sivillage.common.redis.TokenRedisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    //회원가입 시 필요한 repository
    private final CustomerRepository customerRepository;
    private final CustomerMarketingRepository customerMarketingRepository;
    private final CustomerPolicyRepository customerPolicyRepository;
    private final CustomerAdressRepository customerAdressRepository;
    private final CustomerDefaultAddresRepository customerDefaultAddresRepository;
    private final SocialCustomerRepository socialCustomerRepository;

    //로그인 시 필요한 repository
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    //redis
    private final TokenRedisRepository tokenRedisRepository;
    private final TokenBlacklistRepository tokenBlacklistRepository;

    //마이페이지
    private final CustomerSizeRepository customerSizeRepository;


    //이메일 중복 체크
    @Override
    public Optional<Customer> findUserByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public void duplicateEmail(DuplicateEmailDto duplicateEmailDto) {
        Customer customer = findUserByEmail(duplicateEmailDto.getEmail()).orElse(null);

        if (customer != null) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        }
    }


    @Override
    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        //이메일 중복체크
        Customer customer = findUserByEmail(signUpRequestDto.getEmail()).orElse(null);
        if (customer != null) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        }

        try{
            String uuid = UUID.randomUUID().toString();
            customerRepository.save(signUpRequestDto.toCustomerEntity(passwordEncoder, uuid, State.ACTIVATION));
            customerMarketingRepository.save(signUpRequestDto.toMarketingEntity(uuid));
            customerPolicyRepository.save(signUpRequestDto.toPolicyEntity(uuid));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Transactional
    public void updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(updatePasswordRequestDto.getAccessToken());
        Customer customer = customerRepository.findByUuid(uuid).orElse(null);
        if (customer == null) {
            throw new BaseException(BaseResponseStatus.TOKEN_NOT_VALID);
        }

        try {
            // 기존 customer 엔티티 수정 후 저장
            customerRepository.save(updatePasswordRequestDto.updateEntity(customer, passwordEncoder));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateInfo(UpdateInfoRequestDto updateInfoRequestDto) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(updateInfoRequestDto.getAccessToken());
        Customer customer = customerRepository.findByUuid(uuid).orElse(null);
        Marketing marketing = customerMarketingRepository.findByUuid(uuid).orElse(null);
        Policy policy = customerPolicyRepository.findByUuid(uuid).orElse(null);
        if (customer == null || marketing == null || policy == null) {
            throw new BaseException(BaseResponseStatus.TOKEN_NOT_VALID);
        }

        try {
            // 기존 customer 엔티티 수정 후 저장
            customerRepository.save(updateInfoRequestDto.updateEntity(customer));
            customerMarketingRepository.save(updateInfoRequestDto.updateEntity(marketing));
            customerPolicyRepository.save(updateInfoRequestDto.updateEntity(policy));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void createCustomerSize(CustomerSizeRequestDto customerSizeRequestDto) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(customerSizeRequestDto.getAccessToken());

        try{
            customerSizeRepository.save(customerSizeRequestDto.toEntity(uuid));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateCustomerSize(CustomerSizeRequestDto customerSizeRequestDto) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(customerSizeRequestDto.getAccessToken());
        CustomerSize customerSize = customerSizeRepository.findByUuid(uuid).orElse(null);

        if (customerSize == null) {
            throw new BaseException(BaseResponseStatus.TOKEN_NOT_VALID);
        }
        log.info("customerSize 값 가져와짐? {}",customerSize);

        try{
            customerSizeRepository.save(customerSizeRequestDto.updateToEntity(customerSize));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public SignInResponseDto oauthSignIn(OauthSignInRequestDto oauthSignInRequestDto) {
        // 이메일로 회원 검색
        Customer customer = findUserByEmail(oauthSignInRequestDto.getEmail()).orElse(null);

        if (customer != null) {
            // 회원이 존재할 경우 소셜 계정 연결 체크
            Optional<SocialCustomer> socialCustomerOpt = socialCustomerRepository.findByUuidAndOauthProviderId(customer.getUuid(), oauthSignInRequestDto.getOauthProviderId());

            if (socialCustomerOpt.isEmpty()) {
                // 새로운 소셜 계정을 연결
                socialCustomerRepository.save(oauthSignInRequestDto.toEntity(customer.getUuid()));
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                customer, null, customer.getAuthorities());

            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            // Redis에 토큰 저장
            TokenRedis tokenRedis = new TokenRedis(customer.getUuid(), refreshToken);
            tokenRedisRepository.save(tokenRedis);

            return SignInResponseDto.toDto(accessToken, refreshToken);

        }
            // 로그인 처리. null로 보내면 컨트롤러에서 if문 사용해 회원가입 먼저 하라고 오류 출력
        return null;
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        // 1. 이메일로 사용자 찾기
        Customer customer = customerRepository.findByEmail(signInRequestDto.getEmail())
            .orElseThrow(() -> new BaseException(BaseResponseStatus.FAILED_TO_LOGIN));

        try {
            // 2. 사용자 인증 (비밀번호 포함)
            Authentication authentication = authenticate(customer, signInRequestDto.getPassword());

            // 3. 토큰 생성
            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);


            // 4. Redis에 Refresh Token 저장
            TokenRedis tokenRedis = new TokenRedis(customer.getUuid(), refreshToken);
            tokenRedisRepository.save(tokenRedis);

            return SignInResponseDto.toDto(accessToken, refreshToken);

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        }
    }



    //리프레시 토큰을 확인하여 accessToken 재발급
    @Override
    @Transactional
    public RefreshTokenResponseDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(refreshTokenRequestDto.getRefreshToken());

        // Redis에서 리프레시 토큰 검증 //key값이 uuid이기에 uuid로 검색
        TokenRedis tokenRedis = tokenRedisRepository.findById(uuid)
            .orElseThrow(() -> new BaseException(BaseResponseStatus.TOKEN_NOT_VALID));

        log.info("tokenRedis값 제대로 오나?{}",tokenRedis);
        // 리프레시 토큰이 유효하다면 새로운 액세스 토큰 생성
        String customerUuid = tokenRedis.getId();

        // Customer 객체를 principal로 사용하는 Authentication 생성
        Customer customer = customerRepository.findByUuid(customerUuid)
            .orElseThrow(() -> new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            customer, null, customer.getAuthorities());

        // 새로운 AccessToken 생성
        String newAccessToken = jwtTokenProvider.generateAccessToken(authentication);

        // 새로운 AccessToken 반환
        return RefreshTokenResponseDto.toDto(newAccessToken);
    }



    private Authentication authenticate(Customer customer, String inputPassword) {
        AuthUserDetail authUserDetail = new AuthUserDetail(customer);
        return authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authUserDetail.getUsername(),
                inputPassword
            )
        );
    }



}
