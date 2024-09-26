package com.jokim.sivillage.api.customer.application;


import com.jokim.sivillage.api.customer.domain.*;
import com.jokim.sivillage.api.customer.dto.DuplicateEmailDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenRequestDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenResponseDto;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.AddressResponseDto;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;
import com.jokim.sivillage.api.customer.dto.out.SizeResponseDto;
import com.jokim.sivillage.api.customer.entity.AuthUserDetail;
import com.jokim.sivillage.api.customer.infrastructure.*;
import com.jokim.sivillage.api.customer.dto.in.CustomerSizeRequestDto;
import com.jokim.sivillage.api.customer.dto.in.UpdateInfoRequestDto;
import com.jokim.sivillage.api.customer.dto.in.UpdatePasswordRequestDto;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.common.redis.TokenBlacklistRepository;
import com.jokim.sivillage.common.redis.TokenRedis;
import com.jokim.sivillage.common.redis.TokenRedisRepository;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerAddressDefaultListRepository customerAddressDefaultListRepository;
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

        String uuid = UUID.randomUUID().toString();
        customerRepository.save(
            signUpRequestDto.toCustomerEntity(passwordEncoder, uuid, State.ACTIVATION));
        customerMarketingRepository.save(signUpRequestDto.toMarketingEntity(uuid));
        customerPolicyRepository.save(signUpRequestDto.toPolicyEntity(uuid));

    }

    @Override
    @Transactional
    public void updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(
            updatePasswordRequestDto.getAccessToken());
        Customer customer = customerRepository.findByUuid(uuid).orElse(null);
        if (customer == null) {
            throw new BaseException(BaseResponseStatus.TOKEN_NOT_VALID);
        }
        // 기존 customer 엔티티 수정 후 저장
        customerRepository.save(updatePasswordRequestDto.updateEntity(customer, passwordEncoder));
    }

    @Transactional
    @Override
    public void updateInfo(UpdateInfoRequestDto updateInfoRequestDto) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(
            updateInfoRequestDto.getAccessToken());
        Customer customer = customerRepository.findByUuid(uuid).orElse(null);
        Marketing marketing = customerMarketingRepository.findByUuid(uuid).orElse(null);
        Policy policy = customerPolicyRepository.findByUuid(uuid).orElse(null);
        if (customer == null || marketing == null || policy == null) {
            throw new BaseException(BaseResponseStatus.TOKEN_NOT_VALID);
        }

        // 기존 customer 엔티티 수정 후 저장
        customerRepository.save(updateInfoRequestDto.updateEntity(customer));
        customerMarketingRepository.save(updateInfoRequestDto.updateEntity(marketing));
        customerPolicyRepository.save(updateInfoRequestDto.updateEntity(policy));
    }

    @Override
    public void createAddress(CustomerAddressRequestDto customerAddressRequestDto) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(
            customerAddressRequestDto.getAccessToken());

        Address savedAddress = customerAddressRepository.save(
            customerAddressRequestDto.toEntity(uuid)); // 일단 어드레스 저장함.

        List<CustomerAddressDefaultList> customerAddressDefaultList =
            customerAddressDefaultListRepository.findByUuid(uuid);

        // 아래 로직이 defaultAddress 저장하는 로직임
        //create를 통해 들어온 어드레스가 처음 생성한 어드레스라면 create에서 모두 처리
        if (customerAddressDefaultList.isEmpty()) {
            customerAddressDefaultListRepository.save(
                CustomerAddressDefaultListDto.toEntity(uuid, true,
                    savedAddress.getAddressCode()));
        } else {
            //create를 통해 생성한 어드레스가 처음도 아니고, isDefault도 false라면
            customerAddressDefaultListRepository.save(
                CustomerAddressDefaultListDto.toEntity(uuid, false,
                    savedAddress.getAddressCode()));
        }

        //create를 통해 들어온 어드레스가 처음 생성한 어드레스는 아니지만 isDefault가 true라면 setDefault 사용
        if (customerAddressRequestDto.getIsDefault()) {
            CustomerAddressDefaultListDto tem = CustomerAddressDefaultListDto.toDto(uuid,
                customerAddressRequestDto.getAccessToken(),
                savedAddress.getAddressCode(), true);
            setDefaultAddress(tem);

        }
    }

    @Transactional
    @Override
    public void updateAddress(CustomerAddressRequestDto customerAddressRequestDto) { //체크필요
        Address address = customerAddressRepository.findByAddressCode(
            customerAddressRequestDto.getAddressCode()).orElseThrow(()
            -> new BaseException(BaseResponseStatus.NOT_FOUND_ADDRESS));
        Address newAddress = customerAddressRepository.save(
            customerAddressRequestDto.updateEntity(address));

        //update를 통해 들어온 어드레스의 isDefault가 true라면 setDefault 사용
        if (customerAddressRequestDto.getIsDefault()) {
            CustomerAddressDefaultListDto tem = CustomerAddressDefaultListDto.toDto(
                newAddress.getUuid(), customerAddressRequestDto.getAccessToken(),
                newAddress.getAddressCode(), true);
            setDefaultAddress(tem);
        }
    }

    @Override
    public void setDefaultAddress(CustomerAddressDefaultListDto customerAddressDefaultListDto) {
        // JWT를 통해 유저 UUID를 검증하고 가져옴
        String uuid = jwtTokenProvider.validateAndGetUserUuid(
            customerAddressDefaultListDto.getAccessToken());

        String addressCode = customerAddressDefaultListDto.getAddressCode();

        // 기존에 default 주소가 설정된 항목을 찾아서 false로 변경
        CustomerAddressDefaultList customerAddressDefaultList =
            customerAddressDefaultListRepository.findByUuidAndIsDefault(uuid, true)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_ADDRESS));

        customerAddressDefaultListRepository.save(
            customerAddressDefaultListDto.toOldDefaultAddressListEntity(
                customerAddressDefaultList));

        // 새로 설정하려는 주소 코드를 가진 항목을 찾아서 default로 설정
        CustomerAddressDefaultList newDefaultAddress =
            customerAddressDefaultListRepository.findByAddressCode(addressCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_ADDRESS));

        customerAddressDefaultListRepository.save(
            customerAddressDefaultListDto.toNewDefaultAddressListEntity(newDefaultAddress));
    }

    @Transactional
    @Override
    public void deleteAddress(String addressCode) {
        CustomerAddressDefaultList customerAddressDefaultList =
            customerAddressDefaultListRepository.findByAddressCode(addressCode).orElseThrow(()
                -> new BaseException(BaseResponseStatus.NOT_FOUND_ADDRESS));

        if (customerAddressDefaultList.getIsDefault()) {
            throw new BaseException(BaseResponseStatus.NOT_DELETE_DEFAULTADDRESS);
        }
        customerAddressDefaultListRepository.deleteByAddressCode(addressCode);
        customerAddressRepository.deleteByAddressCode(addressCode);
    }

    @Override
    public SizeResponseDto getCustomerSize(String accessToken) {
        // JWT를 통해 유저 UUID를 검증하고 가져옴
        String uuid = jwtTokenProvider.validateAndGetUserUuid(accessToken);
        CustomerSize customerSize = customerSizeRepository.findByUuid(uuid).orElse(null);

        if (customerSize == null) {
            return new SizeResponseDto();
        }

        return SizeResponseDto.toDto(customerSize);
    }


    @Override
    public void saveOrUpdateCustomerSize(CustomerSizeRequestDto customerSizeRequestDto) {
        // accessToken에서 uuid 추출
        String uuid = jwtTokenProvider.validateAndGetUserUuid(
            customerSizeRequestDto.getAccessToken());

        // uuid로 기존 엔티티가 있는지 확인
        CustomerSize existingCustomerSize = customerSizeRepository.findByUuid(uuid).orElse(null);

        // 기존 엔티티가 있으면 업데이트, 없으면 새로 생성
        CustomerSize customerSize = (existingCustomerSize == null)
            ? customerSizeRequestDto.toEntity(uuid)  // 새 엔티티 생성
            : customerSizeRequestDto.updateToEntity(existingCustomerSize);  // 기존 엔티티 업데이트

        // 엔티티 저장
        customerSizeRepository.save(customerSize);
    }

    @Override
    @Transactional
    public SignInResponseDto oauthSignIn(OauthSignInRequestDto oauthSignInRequestDto) {
        // 이메일로 회원 검색
        Customer customer = findUserByEmail(oauthSignInRequestDto.getEmail()).orElse(null);

        if (customer != null) {
            // 회원이 존재할 경우 소셜 계정 연결 체크
            Optional<SocialCustomer> socialCustomerOpt =
                socialCustomerRepository.findByUuidAndOauthProviderId(
                    customer.getUuid(), oauthSignInRequestDto.getOauthProviderId());

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

        // 2. 사용자 인증 (비밀번호 포함)
        Authentication authentication = authenticate(customer, signInRequestDto.getPassword());

        // 3. 토큰 생성
        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        // 4. Redis에 Refresh Token 저장
        TokenRedis tokenRedis = new TokenRedis(customer.getUuid(), refreshToken);
        tokenRedisRepository.save(tokenRedis);

        return SignInResponseDto.toDto(accessToken, refreshToken);

    }


    //리프레시 토큰을 확인하여 accessToken 재발급
    @Override
    @Transactional
    public RefreshTokenResponseDto refreshAccessToken(
        RefreshTokenRequestDto refreshTokenRequestDto) {
        String uuid = jwtTokenProvider.validateAndGetUserUuid(
            refreshTokenRequestDto.getRefreshToken());

        // Redis에서 리프레시 토큰 검증 //key값이 uuid이기에 uuid로 검색
        TokenRedis tokenRedis = tokenRedisRepository.findById(uuid)
            .orElseThrow(() -> new BaseException(BaseResponseStatus.TOKEN_NOT_VALID));

        log.info("tokenRedis값 제대로 오나?{}", tokenRedis);
        // 리프레시 토큰이 유효하다면 새로운 액세스 토큰 생성
        String customerUuid = tokenRedis.getId();

        // Customer 객체를 principal로 사용하는 Authentication 생성
        Customer customer = customerRepository.findByUuid(customerUuid)
            .orElseThrow(() -> new BaseException(BaseResponseStatus.FAILED_CREATE_AUTHORITY));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            customer, null, customer.getAuthorities());

        // 새로운 AccessToken 생성
        String newAccessToken = jwtTokenProvider.generateAccessToken(authentication);

        // 새로운 AccessToken 반환
        return RefreshTokenResponseDto.toDto(newAccessToken);
    }

    @Override
    public List<AddressResponseDto> getAddress(String accessToken) {
        // UUID를 통해 사용자 식별
        String uuid = jwtTokenProvider.validateAndGetUserUuid(accessToken);

        // UUID로 모든 주소 목록을 가져옴
        List<Address> addresses = customerAddressRepository.findByUuid(uuid);

        // 주소 목록이 비어있지 않다면
        if (!addresses.isEmpty()) {
            // 주소 목록을 순회하며 AddressResponseDto로 변환
            List<AddressResponseDto> addressResponseDtos = addresses.stream().map(address -> {
                // 각 주소의 addressCode를 통해 기본 배송지 여부 확인
                Boolean isDefault = customerAddressDefaultListRepository
                    .findByAddressCode(address.getAddressCode())
                    .map(CustomerAddressDefaultList::getIsDefault)
                    .orElse(false); // 기본 배송지가 없으면 false로 설정

                // Address와 isDefault 값을 사용해 AddressResponseDto로 변환
                return AddressResponseDto.toDto(address, isDefault);
            }).toList();

            // 기본 배송지가 있는 주소를 앞에 배치하여 정렬
            return addressResponseDtos.stream()
                .sorted(Comparator.comparing(AddressResponseDto::getIsDefault).reversed())
                .toList();
        }
        // 주소가 없을 경우 빈 리스트 반환
        return List.of();
    }

    @Override
    public AddressResponseDto getAddressDetail(String addressCode) {
        Address address = customerAddressRepository.findByAddressCode(addressCode).orElseThrow(
            () -> new BaseException(BaseResponseStatus.NOT_FOUND_ADDRESS)
        );

        CustomerAddressDefaultList customerAddressDefaultList = customerAddressDefaultListRepository.findByAddressCode(
            addressCode).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_ADDRESS));

        return AddressResponseDto.toDto(address,customerAddressDefaultList.getIsDefault());
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
