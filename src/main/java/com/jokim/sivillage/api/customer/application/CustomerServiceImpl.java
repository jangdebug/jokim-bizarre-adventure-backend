package com.jokim.sivillage.api.customer.application;


import com.jokim.sivillage.api.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.api.common.redis.TokenRedis;
import com.jokim.sivillage.api.common.redis.TokenRedisRepository;
import com.jokim.sivillage.api.customer.domain.*;
import com.jokim.sivillage.api.customer.dto.OauthCustomerSignUpDto;
import com.jokim.sivillage.api.customer.dto.SignInRequestDto;
import com.jokim.sivillage.api.customer.dto.SignInResponseDto;
import com.jokim.sivillage.api.customer.infrastructure.*;
import com.jokim.sivillage.api.customer.dto.CustomerSignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

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
    public void signUp(CustomerSignUpDto customerSignUpDto){

        //UUID생성, 상태 저장
        String customerUuid = UUID.randomUUID().toString();
        State state = State.ACTIVATION;

        Customer customer = customerRepository.findByEmail(customerSignUpDto.getEmail()).orElse(null);

        //이메일 중복체크
        if(customer != null){
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
            DefaultAddress defaultAddress = customerSignUpDto.toDefaultAddressEntity(savedCustomer, savedAddress);
            customerDefaultAddresRepository.save(defaultAddress);
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
            TokenRedis tokenRedis = new TokenRedis(customer.getCustomerUuid(), accessToken, refreshToken);
            tokenRedisRepository.save(tokenRedis);

            return SignInResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("로그인 실패", e);
        }
    }


    private String createToken(Authentication authentication) {
        return jwtTokenProvider.generateAccessToken(authentication);
    }

    private String createRefreshToken(Authentication authentication) {
        return jwtTokenProvider.generateRefreshToken(authentication);
    }
}
