package com.jokim.sivillage.api.customer.application;


import com.jokim.sivillage.api.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.api.common.redis.TokenRedis;
import com.jokim.sivillage.api.common.redis.TokenRedisRepository;
import com.jokim.sivillage.api.customer.domain.*;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.OauthCustomerSignUpResponseDto;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;
import com.jokim.sivillage.api.customer.infrastructure.*;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    //로그인 시 필요한 repository
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    //redis
    private final TokenRedisRepository tokenRedisRepository;

    @Override
    public void signUp(CustomerSignUpDto customerSignUpDto) {
        //UUID생성, 상태 저장
        String customerUuid = UUID.randomUUID().toString();
        State state = State.ACTIVATION;

        Customer customer = customerRepository.findByEmail(customerSignUpDto.getEmail())
            .orElse(null);

        //이메일 중복체크
        if (customer != null) {
            throw new IllegalArgumentException("이미 가입된 회원입니다");
        }

        //회원가입 시 address 입력 받았는지 확인
        Customer newCustomer = customerSignUpDto.toEnity(customerUuid, state, passwordEncoder);
        Customer savedCustomer = customerRepository.save(newCustomer);

        Marketing marketing = customerSignUpDto.toMarketingEntity(savedCustomer);
        customerMarketingRepository.save(marketing);

        Policy policy = customerSignUpDto.toPolicyEntity(savedCustomer);
        customerPolicyRepository.save(policy);

        //회원가입시에 zip_code가 null이면 defaultAddress와 address 저장 X
        Address savedAddress = null;
        if (customerSignUpDto.getZipCode() != null && !customerSignUpDto.getZipCode().isEmpty()) {
            Address address = customerSignUpDto.toAdressEntity();
            savedAddress = customerAdressRepository.save(address);
            DefaultAddress defaultAddress = customerSignUpDto.toDefaultAddressEntity(savedCustomer,
                savedAddress);
            customerDefaultAddresRepository.save(defaultAddress);
        }
    }

    @Override
    public OauthCustomerSignUpResponseDto oauthSignUp(
        OauthCustomerSignUpDto oauthCustomerSignUpDto) {
        String email = oauthCustomerSignUpDto.getEmail();
        Customer customer = customerRepository.findByEmail(email).orElse(null);

        // 소셜아이디 중복체크

        if (customer != null) {
            throw new IllegalArgumentException("이메일 중복입니다.");
        }

        // 새로운 사용자 등록
        String customerUuid = UUID.randomUUID().toString();
        State state = State.ACTIVATION;
        Customer newCustomer = oauthCustomerSignUpDto.toEntity(customerUuid, state);
        customerRepository.save(newCustomer);

        try {
            // Customer 객체를 principal로 사용하는 Authentication 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                newCustomer,  // Principal에 Customer 객체를 넣음
                null,         // 비밀번호는 필요 없으므로 null
                newCustomer.getAuthorities() // 권한 정보 설정
            );
            // JWT 토큰 생성
            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            return OauthCustomerSignUpResponseDto.builder()
                .accessToken(accessToken)
                .uuid(customerUuid)
                .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("소셜회원가입 실패", e);
        }
    }

    //소셜회원가입의 정책부분에 회원가입이 다 끝나면 바로 로그인이 되어야하기에 리턴을 signinResponseDto로 함
    @Override
    public SignInResponseDto oauthpolicySignUp(
        OauthCustomerSignUpPolicyDto oauthCustomerSignUpPolicyDto) {
        // 토큰에서 UUID 추출
        // String uuid = jwtTokenProvider.getUuidFromToken(oauthCustomerSignUpPolicyDto.getAccessToken());

        // UUID로 고객 정보 조회
        String uuid = oauthCustomerSignUpPolicyDto.getUuid();
        Customer customer = customerRepository.findByCustomerUuid(uuid).orElseThrow(
            () -> new IllegalArgumentException("해당 UUID를 가진 회원이 없습니다.")
        );
        log.info("customer.id {}", customer.getId());

        // 여기까진 정삭적으로 처리됨
        // 정책 및 마케팅 정보 저장이 안됨 이거 마저 하면 될듯

        Marketing marketing = Marketing.builder()
            .marketingSms(oauthCustomerSignUpPolicyDto.getMarketingSms())
            .marketingEmail(oauthCustomerSignUpPolicyDto.getMarketingEmail())
            .marketingDm(oauthCustomerSignUpPolicyDto.getMarketingDm())
            .marketingCall(oauthCustomerSignUpPolicyDto.getMarketingCall())
            .customer(customer)
            .build();
        customerMarketingRepository.save(marketing);

        Policy policy = Policy.builder()
            .essential1(oauthCustomerSignUpPolicyDto.getEssential1())
            .essential2(oauthCustomerSignUpPolicyDto.getEssential2())
            .essential3(oauthCustomerSignUpPolicyDto.getEssential3())
            .optional(oauthCustomerSignUpPolicyDto.getOptional())
            .customer(customer)
            .build();
        customerPolicyRepository.save(policy);

        // 주소 저장 (필수 사항이 아닐 경우 처리)
        Address savedAddress = null;
        if (oauthCustomerSignUpPolicyDto.getZipCode() != null
            && !oauthCustomerSignUpPolicyDto.getZipCode().isEmpty()) {
            Address address = Address.builder()
                //.addressName(oauthCustomerSignUpPolicyDto.getAddressName())
                //.recipient(oauthCustomerSignUpPolicyDto.getRecipient())
                //.phone(oauthCustomerSignUpPolicyDto.getPhone())
                .zipCode(oauthCustomerSignUpPolicyDto.getZipCode())
                .address(oauthCustomerSignUpPolicyDto.getAddress())
                .addressDetail(oauthCustomerSignUpPolicyDto.getAddressDetail())
                .message(oauthCustomerSignUpPolicyDto.getMessage())
                .build();
            savedAddress = customerAdressRepository.save(address);

            // 기본 주소 정보 저장
            DefaultAddress defaultAddress = DefaultAddress.builder()
                .isDefault(true)
                .customer(customer)
                .address(savedAddress)
                .build();
            customerDefaultAddresRepository.save(defaultAddress);
        }

        // Authentication 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            customer.getEmail(), null, customer.getAuthorities());

        // AccessToken 및 RefreshToken 생성
        String newAccessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        // Redis에 토큰 저장
        TokenRedis tokenRedis = new TokenRedis(customer.getCustomerUuid(), newAccessToken,
            refreshToken);
        tokenRedisRepository.save(tokenRedis);

        // 로그인 응답 DTO 반환
        return SignInResponseDto.builder()
            .accessToken(newAccessToken)
            .refreshToken(refreshToken)
            .build();
    }

    @Override
    public SignInResponseDto oauthSignIn(
        OauthCustomerSignInRequestDto oauthCustomerSignInRequestDto) {
        Customer customer = customerRepository.findByProvider(
            oauthCustomerSignInRequestDto.getProvider()).orElseThrow(
            () -> new IllegalArgumentException("해당 이메일을 가진 회원과 소셜 경로가 맞지 않습니다")
        );

        try {
            // Customer 객체를 principal로 사용하는 Authentication 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                customer,  // Principal에 Customer 객체를 넣음
                null,         // 비밀번호는 필요 없으므로 null
                customer.getAuthorities() // 권한 정보 설정
            );
            // JWT 토큰 생성
            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            // Redis에 토큰 저장
            TokenRedis tokenRedis = new TokenRedis(customer.getCustomerUuid(), accessToken,
                refreshToken);
            tokenRedisRepository.save(tokenRedis);

            return SignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("소셜회원가입 실패", e);
        }
    }


    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        Customer customer = customerRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(
            () -> new IllegalArgumentException("해당 이메일을 가진 회원이 없습니다.")
        );

        log.info("signInRequestDto : {}", signInRequestDto);
        Authentication authentication;
        try {
            // 아이디와 비밀번호로 토큰 생성
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                customer.getUsername(),
                signInRequestDto.getPassword()
            );

            // 토큰으로 authentication 불러오기
            authentication = authenticationManager.authenticate(token);

            // 불러온 authentication으로 jwt 토큰 생성
            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            log.info("Authentication: {}", authentication);

            // 토큰을 Redis에 저장
            TokenRedis tokenRedis = new TokenRedis(customer.getCustomerUuid(), accessToken,
                refreshToken);
            tokenRedisRepository.save(tokenRedis);

            return SignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("로그인 실패", e);
        }
    }

    @Override
    public void logout(String accessToken) {
        // accessToken을 Redis에서 삭제
        tokenRedisRepository.deleteByAccessToken(accessToken);
        log.info("로그아웃 완료: accessToken {}", accessToken);
    }

}
